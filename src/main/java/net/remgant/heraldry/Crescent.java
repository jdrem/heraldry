package net.remgant.heraldry;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Crescent implements Drawable, java.io.Serializable {

    protected Color color;
    protected double xpos;
    protected double ypos;
    protected double scale;

    public Crescent(Color c, double xpos, double ypos, double scale) {
        this.color = c;
        this.xpos = xpos;
        this.ypos = ypos;
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

    public void draw(Graphics2D g) {
        Area crescent = new Area(crescentShape);
        double x = 200.0 * xpos;
        double y = 255.0 * ypos;
        crescent.transform(AffineTransform.getTranslateInstance(x, y));
        g.setColor(color);
        g.fill(crescent);
    }
}
