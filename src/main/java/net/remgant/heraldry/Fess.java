package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class Fess implements Drawable, java.io.Serializable {
    Tincture tincture;


    public Fess(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        tincture.fill(g, new Area(new Rectangle2D.Float(0.0f, 95.0f, 200.0f, 60.0f)));
    }
}

