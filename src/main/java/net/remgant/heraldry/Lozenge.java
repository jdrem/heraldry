package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Lozenge extends Charge {
    protected Lozenge(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static protected Shape shape;
    static {
        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.866, 0.0);
        path.lineTo(0.0, 1.0);
        path.lineTo(-0.866, 0.0);
        path.lineTo(0.0, -1.0);
        path.lineTo(0.866, 0.0);
        Area area = new Area(path);
        area.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        shape = area;
    }
    @Override
    protected Shape getShape() {
        return shape;
    }
}
