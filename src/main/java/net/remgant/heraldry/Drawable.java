package net.remgant.heraldry;

import java.awt.Graphics2D;

public interface Drawable {
    void draw(Graphics2D graphics);

    void draw(java.awt.image.BufferedImage image);
}
