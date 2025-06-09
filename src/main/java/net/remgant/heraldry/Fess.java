package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.lang.management.ThreadInfo;

class Fess implements Drawable, java.io.Serializable {
    Tincture tincture;


    public Fess(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g) {
        tincture.fill(g, new Area(new Rectangle2D.Float(0.0f, 70.0f, 200.0f, 60.0f)));
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}

