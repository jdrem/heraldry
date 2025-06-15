package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class FessIndented implements Drawable, java.io.Serializable {
    Tincture tincture;

    public FessIndented(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area fess = new Area();
        float top = 65.0f;
        float bot = 135.0f;
        fess.add(new Area(new Rectangle2D.Float(0.0f, top, 200.0f, bot - top)));
        Rectangle2D.Float r = new Rectangle2D.Float();
        Area a;
        AffineTransform ta = new AffineTransform();
        AffineTransform tb = new AffineTransform();
        tb.rotate(Math.PI / 4.0);

        for (int i = 0; i < 11; i++) {
            r.setRect(-6.75f, -6.75f, 13.5f, 13.5f);
            a = new Area(r);
            a.transform(tb);
            ta.setToIdentity();
            ta.translate((double) i * 20.0, top);
            a.transform(ta);
            fess.subtract(a);
            ta.setToIdentity();
            ta.translate(0.0, bot - top);
            a.transform(ta);
            fess.subtract(a);
        }

        tincture.fill(g, fess);
    }
}

