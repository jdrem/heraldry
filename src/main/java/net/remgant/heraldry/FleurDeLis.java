package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;
import java.lang.management.ThreadInfo;

class FleurDeLis implements Drawable, java.io.Serializable {

    protected Tincture tincture;
    protected Shield.Position position;
    protected double scale;

    public FleurDeLis(Tincture tincture, Shield.Position position, double scale) {
        this.tincture = tincture;
        this.position = position;
        this.scale = scale;
    }

    final static Shape fleurDeLisShape;
    static {
        Ellipse2D.Float e1 = new Ellipse2D.Float(-25.0f, -10.0f, 20.0f, 20.0f);
        Ellipse2D.Float e2 = new Ellipse2D.Float(-30.0f, -5.0f, 20.0f, 20.0f);
        Area f1 = new Area();
        f1.add(new Area(e1));
        f1.subtract(new Area(e2));
        Area fleur = new Area();
        fleur.add(f1);
        e1 = new Ellipse2D.Float(5.0f, -10.0f, 20.0f, 20.0f);
        e2 = new Ellipse2D.Float(10.0f, -5.0f, 20.0f, 20.0f);
        f1 = new Area();
        f1.add(new Area(e1));
        f1.subtract(new Area(e2));
        fleur.add(f1);
        e1 = new Ellipse2D.Float(-5.0f, -27.5f, 10.0f, 40.0f);
        fleur.add(new Area(e1));
        RoundRectangle2D.Float rr = new RoundRectangle2D.Float
                (-15.0f, -0.0f, 30.0f, 5.0f, 2.0f, 2.0f);
        fleur.add(new Area(rr));
        fleurDeLisShape = fleur;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area fleur = new Area(fleurDeLisShape);
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity())
            fleur.transform(affineTransform);
        if (scale != 1.0) {
            fleur.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = position.x() * shield.getBounds2D().getWidth();
        double y = position.y() * shield.getBounds2D().getHeight();
        fleur.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(g, fleur);
    }
}
