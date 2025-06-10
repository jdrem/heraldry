package net.remgant.heraldry;

import java.awt.Graphics2D;

public interface Drawable {
    void draw(Graphics2D graphics);

    default void draw(java.awt.image.BufferedImage image) {
        draw(image.createGraphics());
    }
}
