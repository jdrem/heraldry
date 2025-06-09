package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Saltire implements Drawable {
    Color color;

    public Saltire(Color c) {
        color = c;
    }

    public void draw(Graphics2D g) {
        Area saltire = new Area();
        Area a = new Area(new Rectangle2D.Float(0.0f, -30.0f, 300.0f, 60.0f));
        a.transform(AffineTransform.getRotateInstance(Math.PI/4.0));
        saltire.add(a);

        Area b = new Area(new Rectangle2D.Float(0.0f, -30.0f, 300.0f, 60.0f));
        b.transform(AffineTransform.getScaleInstance(-1.0, 1.0));
        b.transform(AffineTransform.getRotateInstance(-Math.PI/4.0));
        b.transform(AffineTransform.getTranslateInstance(200.0, 0.0));
        saltire.add(b);

        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        saltire.intersect(shield);
        g.setColor(color);
        g.fill(saltire);
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}
