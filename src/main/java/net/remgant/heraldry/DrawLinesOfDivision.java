package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.random.RandomGenerator;

public class DrawLinesOfDivision {

    public static void main(String[] args) {
        new DrawLinesOfDivision().run();
    }
    public void run() {
        String imageFileType = "SVG";
        int width = Shield.LineOfDivision.values().length / 2;
        int height = 2;
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
        for (int j = 0; j<height; j++) {
            for (int i = 0; i < width; i++) {
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        (double)spacing + (double)i*((double)spacing + 200.0),
                        (double)spacing + (double)j*((double)spacing + 250.0));
                Shield.LineOfDivision lineOfDivision = Shield.LineOfDivision.values()[j*4 + i+1];
                builder.add(new Shield(Tincture.OR, Tincture.AZURE, lineOfDivision));
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("lines-of-division.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
