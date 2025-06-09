package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Cross implements Drawable {
    Color color;

    public Cross(Color c) {
        color = c;
    }

    public void draw(Graphics2D g) {
        Area cross = new Area();
        cross.add(new Area(new Rectangle2D.Float(70.0f, 0.0f, 60.0f, 250.0f)));
        cross.add(new Area(new Rectangle2D.Float(0.0f, 70.0f, 200.0f, 60.0f)));

        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        cross.intersect(shield);
        g.setColor(color);
        g.fill(cross);
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}
