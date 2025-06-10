package net.remgant.heraldry;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Star implements Drawable, java.io.Serializable {

    protected Color color;
    protected double xpos;
    protected double ypos;
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

    public Star(Color c, double xpos, double ypos, double scale) {
        this.color = c;
        this.xpos = xpos;
        this.ypos = ypos;
        this.scale = scale;
    }

    public void draw(Graphics2D g) {
        Area star = new Area(starShape);
        double x = 200.0 * xpos;
        double y = 255.0 * ypos;
        star.transform(AffineTransform.getTranslateInstance(x, y));
        g.setColor(color);
        g.fill(star);
    }
}
