package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Escutcheon extends Charge {
    protected Escutcheon(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }
    private static final Shape shape;
    static {
        Area a = new Area(Shield.shieldShape);
        double d = Math.max(a.getBounds2D().getWidth(), a.getBounds2D().getHeight());
        double e = 60 / d;
        a.transform(AffineTransform.getScaleInstance(e, e));
        a.transform(AffineTransform.getTranslateInstance(-a.getBounds2D().getWidth()/2.0, -a.getBounds2D().getHeight()/2.0));
        shape = a;
    }
    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public String defArticle() {
        return "an";
    }
}
