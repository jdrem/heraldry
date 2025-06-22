package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class Mascle extends Charge {
    protected Mascle(Tincture tincture, Shield.Position position, double scale) {
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
        path.transform(AffineTransform.getScaleInstance(0.6, 0.6));
        area.subtract(new Area(path));
        area.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        shape = area;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
