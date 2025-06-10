package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class Crescent implements Drawable, java.io.Serializable {
    protected Tincture tincture;
    protected Shield.Position position;
    protected double scale;

    public Crescent(Tincture tincture, Shield.Position position, double scale) {
        this.tincture = tincture;
        this.position = position;
        this.scale = scale;
    }

    final static Shape crescentShape;
    static {
        Area crescent = new Area();
        Ellipse2D.Float c1 = new Ellipse2D.Float(-15.0f, -15.0f, 30.0f, 30.0f);
        Ellipse2D.Float c2 = new Ellipse2D.Float(-9.0f, -15.5f, 18.0f, 18.0f);
        crescent.add(new Area(c1));
        crescent.subtract(new Area(c2));
        crescentShape = crescent;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area crescent = new Area(crescentShape);
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity())
            crescent.transform(affineTransform);
        if (scale != 1.0) {
            crescent.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = position.x() * shield.getBounds2D().getWidth();
        double y = position.y() * shield.getBounds2D().getHeight();
        crescent.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(g, crescent);
    }
}
