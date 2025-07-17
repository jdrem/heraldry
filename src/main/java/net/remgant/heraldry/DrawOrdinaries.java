package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.IntStream;

public class DrawOrdinaries {

    public static void main(String[] args) {
        new DrawOrdinaries().run();
    }

    public void run() {
        Function<Tincture, Drawable> bend = Bend::new;
        Function<Tincture, Drawable> bendSinister = BendSinister::new;
        Function<Tincture, Drawable> chevron = Chevron::new;
        Function<Tincture, Drawable> chief = Chief::new;
        Function<Tincture, Drawable> cross = Cross::new;
        Function<Tincture, Drawable> fess = Fess::new;
        Function<Tincture, Drawable> pale = Pale::new;
        Function<Tincture, Drawable> saltire = Saltire::new;

        Function<Tincture,Drawable>[][] ordinaries = new Function[][] {
                new Function[]{chief, fess, pale, bend},
                new Function[]{bendSinister, chevron, saltire, cross}
        };

        String imageFileType = "SVG";
        int width = IntStream.of(ordinaries[0].length, ordinaries[1].length).max().getAsInt();
        int height = ordinaries.length;
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
        for (int j = 0; j<ordinaries.length; j++) {
            for (int i = 0; i < ordinaries[j].length; i++) {
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        (double)spacing + (double)i*((double)spacing + 200.0),
                        (double)spacing + (double)j*((double)spacing + 250.0));
                builder.add(new Shield(Tincture.OR));
                builder.add(ordinaries[j][i], Tincture.SABLE);
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("ordinaries.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
