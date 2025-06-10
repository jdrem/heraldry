package net.remgant.heraldry;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface Drawable {
    void draw(Graphics2D graphics, AffineTransform affineTransform);

    default void draw(Graphics2D graphics) {
        draw(graphics, new AffineTransform());
    }
    default void draw(java.awt.image.BufferedImage image) {
        draw(image.createGraphics(), new AffineTransform());
    }
}
