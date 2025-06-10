package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

public class Heart extends Charge {
    protected Heart(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }
    final static protected Shape shape;
    static {
        Area area = new Area();
        Ellipse2D.Float e1 = new Ellipse2D.Float(-1.0f, -0.5f, 1.0f, 1.0f);
        Ellipse2D.Float e2 = new Ellipse2D.Float(0.0f, -0.5f, 1.0f, 1.0f);
        Rectangle2D.Float r = new Rectangle2D.Float(-1.0f, 0.0f, 2.0f, 1.0f);
        area.add(new Area(e1));
        area.add(new Area(e2));
        area.subtract(new Area(r));
        Path2D.Float path = new Path2D.Float();
        path.moveTo(-1.0f, 0.0);
        path.lineTo(1.0, 0.0);
        path.lineTo(0.0f, 1.0f);
        path.lineTo(-1.0f, 0.0f);
        area.add(new Area(path));
        area.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        shape = area;
    }
    @Override
    protected Shape getShape() {
        return shape;
    }
}
