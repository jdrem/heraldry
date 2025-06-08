package net.remgant.heraldry;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class FessInvected implements Drawable, java.io.Serializable {
    Color color;

    public FessInvected(Color c) {
        color = c;
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }

    public void draw(Graphics2D g) {
        Area fess = new Area();
        float top = 75.0f;
        float bot = 125.0f;
        fess.add(new Area(new Rectangle2D.Float(0.0f, top, 200.0f, bot - top)));

        Ellipse2D.Float e = new Ellipse2D.Float();
        for (int i = 0; i < 10; i++) {
            e.setFrame((float) i * 20.0f, top - 10.0f, 20.0f, 20.0f);
            fess.add(new Area(e));
            e.setFrame((float) i * 20.0f, bot - 10.0f, 20.0f, 20.0f);
            fess.add(new Area(e));
        }

        g.setColor(color);
        g.fill(fess);
    }
}

