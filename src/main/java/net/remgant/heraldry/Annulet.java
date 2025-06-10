package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Annulet extends Charge {
    protected Annulet(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static Shape shape;
    static {
       Area e1 = new Area(new Ellipse2D.Float(-15.0f, -15.0f, 30.0f, 30.0f));
       Area e2 = new Area(new Ellipse2D.Float(-10.0f, -10.0f, 20.0f, 20.0f));
       e1.subtract(e2);
       shape = e1;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
