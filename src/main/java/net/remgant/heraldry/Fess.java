package net.remgant.heraldry;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Fess implements Drawable, java.io.Serializable {
    Color color;

    public Fess(Color c) {
        color = c;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fill(new Area(new Rectangle2D.Float(0.0f, 70.0f, 200.0f, 60.0f)));
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}

