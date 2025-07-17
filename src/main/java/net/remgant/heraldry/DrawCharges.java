package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.stream.IntStream;

public class DrawCharges {
    public static void main(String[] args) {
        new DrawCharges().run();
    }

    public void run() {
        MultiFunction<Tincture, Shield.Position, Double, Drawable> annulet = Annulet::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> crescent = Crescent::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> ermineSpot = ErmineSpot::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> fleurDeLis = FleurDeLis::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> heart = Heart::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> lozenge = Lozenge::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> martlet = Martlet::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> rose = Rose::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> roundel = Roundel::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> star = Star::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> mascle = Mascle::new;
        MultiFunction<Tincture, Shield.Position, Double, Drawable> escutcheon = Escutcheon::new;


        MultiFunction<Tincture, Shield.Position, Double, Drawable>[][] charges = new MultiFunction[][]{
                new MultiFunction[]{annulet, crescent, fleurDeLis, heart, lozenge},
                new MultiFunction[]{martlet, star, rose, roundel}
        };

        String imageFileType = "SVG";
        int width = IntStream.of(charges[0].length, charges[1].length).max().getAsInt();
        int height = charges.length;
        int spacing = 40;
        int xDim = spacing + width * (spacing + 200);
        int yDim = spacing + height * (spacing + 250);
        Properties properties = new Properties();
        properties.setProperty("width", String.format("%d",xDim));
        properties.setProperty("height", String.format("%d", yDim));
        FileWriterFactory fileWriterFactory = new FileWriterFactory(imageFileType, properties);
        FileWriter fileWriter = fileWriterFactory.getInstance();
        Builder builder = new Builder(fileWriter);
        Graphics2D graphics = fileWriter.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        graphics.fill(new Rectangle2D.Double(0.0, 0.0, spacing + (double)width * ((double)spacing + 200.0), (double)spacing + (double)height*((double)spacing + 250.0)));
        // unset transperancy
        graphics.setComposite(AlphaComposite.SrcOver);
        double extraSpacing = 0;
        for (int j = 0; j<charges.length; j++) {
            for (int i = 0; i < charges[j].length; i++) {
                if (j == charges.length-1 && charges[j].length != charges[j-1].length)
                    extraSpacing = Shield.shieldShape.getBounds2D().getWidth() / 2.0;
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        extraSpacing + (double)spacing + (double)i*((double)spacing + 200.0),
                        (double)spacing + (double)j*((double)spacing + 250.0));
                builder.add(new Shield(Tincture.GULES));
                builder.add(charges[j][i], Tincture.ARGENT, Shield.Position.CENTER, 3.0);
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("charges.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
