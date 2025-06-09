package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class Chevron implements Drawable {
    Color color;

    public Chevron(Color c) {
        color = c;
    }

    public void draw(Graphics2D g) {
        Area chevron = new Area();

        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(-139.0f, 250.0);
        path.lineTo(130.0f, 250.0);
        path.lineTo(0.0f, 0.0f);
        chevron.add(new Area(path));
        path.transform(AffineTransform.getTranslateInstance(0.0, 110.0f));
        chevron.subtract(new Area(path));
        chevron.transform(AffineTransform.getTranslateInstance(100.0f, 20.0f));

        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        chevron.intersect(shield);
        g.setColor(color);
        g.fill(chevron);
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }
}
