package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.stream.IntStream;

public class DrawArrangementOfCharges {
    public static void main(String[] args) {
        new DrawArrangementOfCharges().run();
    }

    public void run() {
        Shield.Position[][][] positions = new Shield.Position[][][]{
                new Shield.Position[][] {
                        // default
                        new Shield.Position[]{Shield.Position.HONOR_POINT_DEXTER, Shield.Position.NAVEL_POINT, Shield.Position.HONOR_POINT_SINISTER},
                        //  in fess
                        new Shield.Position[]{Shield.Position.FESS_DEXTER, Shield.Position.CENTER, Shield.Position.FESS_SINISTER},
                        // in pale
                        new Shield.Position[]{Shield.Position.PALE_TOP, Shield.Position.CENTER,  Shield.Position.PALE_BOTTOM},
                        // in chief
                        new Shield.Position[]{Shield.Position.CHIEF_DEXTER, Shield.Position.CHIEF_CENTER, Shield.Position.CHIEF_SINISTER},
                },
                new Shield.Position[][] {
                        // in bend
                        new Shield.Position[]{Shield.Position.BEND_TOP_DEXTER, Shield.Position.CENTER, Shield.Position.BEND_BOTTOM_SINISTER},
                        // in bend sinister
                        new Shield.Position[]{Shield.Position.BEND_BOTTOM_DEXTER, Shield.Position.CENTER, Shield.Position.BEND_TOP_SINISTER},
                        // in saltire
                        new Shield.Position[]{Shield.Position.BEND_TOP_DEXTER, Shield.Position.BEND_TOP_SINISTER, Shield.Position.CENTER, Shield.Position.BEND_BOTTOM_DEXTER, Shield.Position.BEND_BOTTOM_SINISTER},
                        // in cross
                        new Shield.Position[]{Shield.Position.PALE_TOP, Shield.Position.CENTER, Shield.Position.PALE_BOTTOM, Shield.Position.FESS_DEXTER, Shield.Position.FESS_SINISTER}
                }
        };

        String imageFileType = "SVG";
        int width = IntStream.of(positions[0].length, positions[1].length).max().getAsInt();
        int height = positions.length;
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
        for (int j = 0; j<positions.length; j++) {
            for (int i = 0; i < positions[j].length; i++) {
                if (j == positions.length-1 && positions[j].length != positions[j-1].length)
                    extraSpacing = Shield.shieldShape.getBounds2D().getWidth() / 2.0;
                AffineTransform affineTransform = AffineTransform.getTranslateInstance(
                        extraSpacing + (double)spacing + (double)i*((double)spacing + 200.0),
                        (double)spacing + (double)j*((double)spacing + 250.0));
                builder.add(new Shield(Tincture.AZURE));
                for (int k=0; k<positions[j][i].length; k++) {
                    builder.add(new Star(Tincture.ARGENT, positions[j][i][k], 1.0));
                }
                builder.build(affineTransform, fw -> {});
                builder.reset();
            }
        }
        try {
            fileWriter.writeToFile("arrangement-of-charges.svg");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
