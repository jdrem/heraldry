package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Label implements Drawable {
    Tincture tincture;

    public Label(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area label = new Area();
        label.add(new Area(new Rectangle2D.Float(0.0f, 25.0f, 200.0f, 30.0f)));
        label.add(new Area(new Rectangle2D.Float(30.0f, 55.0f, 30.0f, 30.0f)));
        label.add(new Area(new Rectangle2D.Float(85.0f, 55.0f, 30.0f, 30.0f)));
        label.add(new Area(new Rectangle2D.Float(140.0f, 55.0f, 30.0f, 30.0f)));
        tincture.fill(g, label);
    }
}
