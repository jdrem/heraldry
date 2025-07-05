package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.random.RandomGenerator;

public class DrawRandomSinglePage extends DrawRandom {

    public static void main(String[] args) {
        DrawRandom drawRandom = new DrawRandomSinglePage();
        drawRandom.run(args);
    }

    public void run(String[] args) {
        String imageFileType = "SVG";

        int width = 12;
        int height = 12;
        int xDim = 25 + width * 225;
        int yDim = 25 + height * 275;
        Properties properties = new Properties();
        properties.setProperty("width", String.format("%d",xDim));
        properties.setProperty("height", String.format("%d", yDim));
        FileWriterFactory fileWriterFactory = new FileWriterFactory(imageFileType, properties);
        FileWriter fileWriter = fileWriterFactory.getInstance();
        Builder builder = new Builder(fileWriter);
        Graphics2D graphics = fileWriter.createGraphics();
        graphics.setColor(new Color(224, 171, 118));
        graphics.fill(new Rectangle2D.Double(0.0, 0.0, 25.0 + (double)width * 225.0, 25.0 + (double)height*275.0));
        for (int j = 0; j<height; j++) {
            for (int i = 0; i < width; i++) {
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        25.0 + (double)i*225.0,
                        25.0 + (double)j*275.0);
                RandomGenerator random = RandomGenerator.getDefault();
                double d = random.nextDouble();
                Tincture fieldTincture = null;
                Tincture chargeTincture = null;
                Tincture ordinaryTincture = null;
                Tincture t1 = null;
                Tincture t2 = null;
                Shield.LineOfDivision lineOfDivision = null;
                if (d < 0.15) {
                    // split the field
                    t1 = pickAnyTincture(random);
                    t2 = pickAnyTinctureBut(random, Set.of(t1));
//                while (t2.equals(t1))
//                    t2 = pickAnyTincture(random);
                    int ii = random.nextInt(Shield.LineOfDivision.values().length);
                    lineOfDivision = Shield.LineOfDivision.values()[ii];
                    builder.add(new Shield(t1, t2, lineOfDivision));
                } else {
                    fieldTincture = pickAnyTincture(random);
                    builder.add(new Shield(fieldTincture));
                }
                // now decide what to do
                d = random.nextDouble();
                if (d < 0.60 && lineOfDivision != null &&
                        !(t1.isFur() && t2.isFur()) &&
                        !(t1.isMetal() && t2.isMetal()) &&
                        !(t1.isColor() && t2.isColor())) {
                    // Do a counter charge
                    MultiFunction<Tincture, Shield.Position, Double, Drawable> chargeFunc = charges.get(random.nextInt(charges.size()));
                    switch (lineOfDivision) {
                        case PER_PALE:
                        case PER_BEND:
                        case PER_BEND_SINISTER:
                            builder.add(chargeFunc, t2, Shield.Position.FESS_DEXTER_MID, 2.0);
                            builder.add(chargeFunc, t1, Shield.Position.FESS_SINISTER_MID, 2.0);
                            break;
                        case PER_FESS:
                            builder.add(chargeFunc, t2, Shield.Position.PALE_TOP_MID, 2.0);
                            builder.add(chargeFunc, t1, Shield.Position.PALE_BOTTOM_MID, 2.0);
                            break;
                    }
                } else if (d < 0.40) {
                    // do an ordinary
                    Function<Tincture, Drawable> func = ordinaries.get(random.nextInt(ordinaries.size()));
                    if (fieldTincture == null && t1 != null && t2 != null) {
                        ordinaryTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                    } else if (fieldTincture != null && fieldTincture.isMetal())
                        ordinaryTincture = pickNonMetal(random);
                    else
                        ordinaryTincture = pickNonColor(random);
                    builder.add(func, ordinaryTincture);
                    // should we add charges to the ordinary?
                    d = random.nextDouble();
                    if (d < 0.35 && fieldTincture != null) {
                        // Add charges to ordinary
                        MultiFunction<Tincture, Shield.Position, Double, Drawable> chargeFunc = charges.get(random.nextInt(charges.size()));
                        String simpleName = func.apply(ordinaryTincture).getClass().getSimpleName();
                        List<Shield.Position> positions = new ArrayList<>();
                        d = random.nextDouble();
                        if (d < 0.5) {
                            chargeTincture = fieldTincture;
                        } else {
                            if (ordinaryTincture.isColor())
                                chargeTincture = pickNonColor(random);
                            else if (ordinaryTincture.isMetal())
                                chargeTincture = pickNonMetal(random);
                            else
                                chargeTincture = pickAnyTinctureBut(random, Set.of(fieldTincture, ordinaryTincture));
                        }
                        Shield.ArrangementOfCharges arrangementOfCharges = Shield.ArrangementOfCharges.map.get(simpleName);
                        if (arrangementOfCharges != null)
                            builder.add(chargeFunc, chargeTincture, arrangementOfCharges);
                    }
                } else if (d < 0.55) {
                    // a single charge
                    MultiFunction<Tincture, Shield.Position, Double, Drawable> func = charges.get(random.nextInt(charges.size()));
                    if (fieldTincture == null) {
                        chargeTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                    } else if (fieldTincture.isMetal())
                        chargeTincture = pickNonMetal(random);
                    else
                        chargeTincture = pickNonColor(random);
                    builder.add(func, chargeTincture, Shield.Position.CENTER, 3.0);
                } else {
                    // multiple charges.
                    MultiFunction<Tincture, Shield.Position, Double, Drawable> func = charges.get(random.nextInt(charges.size()));
                    if (fieldTincture == null) {
                        chargeTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                    } else if (fieldTincture.isMetal())
                        chargeTincture = pickNonMetal(random);
                    else
                        chargeTincture = pickNonColor(random);
                    int ii = random.nextInt(Shield.ArrangementOfCharges.allArrangements.length);

                    builder.add(func, chargeTincture, Shield.ArrangementOfCharges.allArrangements[ii]);
                }
                d = random.nextDouble();
                if (d < 0.025) {
                    // add a border
                    Set<Tincture> ft = new HashSet<>();
                    if (t1 != null && t2 != null) {
                        ft.add(t1);
                        ft.add(t2);
                    } else {
                        ft.add(fieldTincture);
                    }
                    Tincture borderTincture = pickAnyTinctureBut(random, ft);
                    builder.add(new Border(borderTincture));
                }
                d = random.nextDouble();
                if (d < 0.025) {
                    // add a border
                    Set<Tincture> ft = new HashSet<>();
                    if (t1 != null && t2 != null) {
                        ft.add(t1);
                        ft.add(t2);
                    } else {
                        ft.add(fieldTincture);
                    }
                    if (chargeTincture != null) {
                        ft.add(chargeTincture);
                    }
                    if (ordinaryTincture != null) {
                        ft.add(ordinaryTincture);
                    }
                    Tincture borderTincture = pickAnyTinctureBut(random, ft);
                    builder.add(new Label(borderTincture));
                }
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("aaaaa-0001.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
