package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class ErmineSpot extends Charge {
    public ErmineSpot(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static Shape shape;
    static {
        Area e1 = new Area(new Ellipse2D.Float(-1.5f, 0.0f, 1.0f, 1.0f));
        Area e2 = new Area(new Ellipse2D.Float(-0.5f, -1.0f, 1.0f, 1.0f));
        Area e3 = new Area(new Ellipse2D.Float(0.5f, 0.0f, 1.0f, 1.0f));
        Area area = new Area();
        area.add(e1);
        area.add(e2);
        area.add(e3);
        Path2D.Float p1 = new Path2D.Float();
        p1.moveTo(0.0, 0.0);
        p1.lineTo(-1.5, 5.0);
        p1.lineTo(1.5, 5.0);
        p1.lineTo(0.0, 0.0);
        area.add(new Area(p1));
        Path2D.Float p2 = new Path2D.Float();
        p2.moveTo(0.0, 3.0);
        p2.lineTo(-1.5, 5.0);
        p2.lineTo(1.5, 5.0);
        p2.lineTo(0.0, 3.0);
        area.subtract(new Area(p2));
        double d = Math.max(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
        double scale = 30.0 / d;
        area.transform(AffineTransform.getScaleInstance(scale, scale));
        d = area.getBounds2D().getHeight() / 2.0;
        d = d + area.getBounds2D().getY();
        area.transform(AffineTransform.getTranslateInstance(0.0, -d));
        shape = area;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
