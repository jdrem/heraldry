package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.stream.IntStream;

public class DrawTinctures {
    public static void main(String[] args) {
        new DrawTinctures().run();
    }

    public void run() {
        Tincture[][] tinctures = new Tincture[][]{
                new Tincture[]{Tincture.OR, Tincture.ARGENT},
                new Tincture[]{Tincture.GULES, Tincture.SABLE, Tincture.AZURE, Tincture.VERT, Tincture.PURPURE},
            new Tincture[]{Tincture.ERMINE, Tincture.ERMINES, Tincture.ERMINOIS, Tincture.PEAN, Tincture.VAIR}
        };
        String imageFileType = "SVG";
        int width = IntStream.of(tinctures[0].length, tinctures[1].length, tinctures[2].length).max().getAsInt();
        int height = tinctures.length;
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
        for (int j = 0; j<tinctures.length; j++) {
            for (int i = 0; i < tinctures[j].length; i++) {
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        (double)spacing + (double)i*((double)spacing + 200.0),
                        (double)spacing + (double)j*((double)spacing + 250.0));
                builder.add(new Shield(tinctures[j][i]));
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("metals-colors-furs.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
