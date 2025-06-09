package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Pale implements Drawable {
    Tincture tincture;

    public Pale(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g) {
        Area pale = new Area();
        pale.add(new Area(new Rectangle2D.Float(70.0f, 0.0f, 60.0f, 250.0f)));
        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        pale.intersect(shield);
        tincture.fill(g, pale);
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}
