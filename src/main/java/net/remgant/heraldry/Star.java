package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class Star implements Drawable, java.io.Serializable {
    protected Tincture tincture;
    protected Shield.Position position;
    protected double scale;
    final static Shape starShape;
    static {
        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(-0.723f, 0.0f);
        path.lineTo(0.0f, -1.0f);
        path.lineTo(0.723f, 0.0f);
        path.lineTo(0.0f, 0.0f);
        Area arm = new Area(path);
        Area star = new Area();
        for (int i = 0; i < 5; i++) {
            star.add(arm);
            arm.transform(AffineTransform.getRotateInstance(2.0 * Math.PI / 5.0));
        }
        star.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        starShape = star;
    }

    public Star(Tincture tincture, Shield.Position position, double scale) {
        this.tincture = tincture;
        this.position = position;
        this.scale = scale;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area star = new Area(starShape);
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity())
            star.transform(affineTransform);
        if (scale != 1.0) {
            star.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = position.x() * shield.getBounds2D().getWidth();
        double y = position.y() * shield.getBounds2D().getHeight();

        star.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(g, star);
    }
}
