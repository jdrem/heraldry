package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class FessEngrailed implements Drawable, java.io.Serializable {
    final private Tincture tincture;

    public FessEngrailed(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area fess = new Area();
        float top = 90.0f;
        float bot = 160.0f;
        fess.add(new Area(new Rectangle2D.Float(0.0f, top, 200.0f, bot - top)));

        Ellipse2D.Float e = new Ellipse2D.Float();
        for (int i = 0; i < 10; i++) {
            e.setFrame((float) i * 20.0f, top - 10.0f, 20.0f, 20.0f);
            fess.subtract(new Area(e));
            e.setFrame((float) i * 20.0f, bot - 10.0f, 20.0f, 20.0f);
            fess.subtract(new Area(e));
        }
        tincture.fill(g, fess);
    }
}

