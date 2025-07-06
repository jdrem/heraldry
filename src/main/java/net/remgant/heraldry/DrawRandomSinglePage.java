package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
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
        RandomGenerator random = RandomGenerator.getDefault();
        graphics.setColor(new Color(224, 171, 118));
        graphics.fill(new Rectangle2D.Double(0.0, 0.0, 25.0 + (double)width * 225.0, 25.0 + (double)height*275.0));
        for (int j = 0; j<height; j++) {
            for (int i = 0; i < width; i++) {
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        25.0 + (double)i*225.0,
                        25.0 + (double)j*275.0);
                drawShield(builder, random);
                builder.build(affineTransform, fw -> {});
                System.out.printf("%d %d %s%n", i, j, builder.getDescription());
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
