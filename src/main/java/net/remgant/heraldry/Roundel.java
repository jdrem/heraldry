package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Roundel extends Charge {

    final static Shape shape;
    static {
        shape = new Area(new Ellipse2D.Float(-15.0f, -15.0f, 30.0f, 30.0f));
    }

    protected Roundel(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    @Override
    protected Shape getShape() {
        return shape;
    }
}
