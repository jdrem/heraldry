package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.*;

class BorderInvected extends Border {
    public BorderInvected(Color c) {
        super(c);
    }

    public BorderInvected(Color[] c, int division) {
        super(c, division);
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area border = new Area();
        Rectangle2D.Float outerR =
                new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f);
        Ellipse2D.Float outerC =
                new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f);
        border.add(new Area(outerR));
        border.add(new Area(outerC));

        Rectangle2D.Float innerR =
                new Rectangle2D.Float(10.0f, 10.0f, 180.0f, 135.0f);
        Ellipse2D.Float innerC =
                new Ellipse2D.Float(10.0f, 50.0f, 180.0f, 190.0f);
        border.subtract(new Area(innerR));
        border.subtract(new Area(innerC));

        // Invect the top edge of the border

        Ellipse2D.Float ee = new Ellipse2D.Float(0.0f, 0.0f, 15.0f, 15.0f);
        Area e = new Area(ee);
        AffineTransform t = new AffineTransform();

        t.translate(0.0f, 2.5f);
        e.transform(t);
        t.setToIdentity();
        t.translate(15.0f, 0.0f);
        for (int i = 0; i < 11; i++) {
            e.transform(t);
            border.add(e);
        }

        // do both sides
        ee = new Ellipse2D.Float(0.0f, 0.0f, 15.0f, 15.0f);
        Area e1 = new Area(ee);
        Area e2 = new Area(ee);
        t.setToIdentity();
        t.translate(2.5f, 0.0f);
        e1.transform(t);
        t.setToIdentity();
        t.translate(182.5f, 0.0f);
        e2.transform(t);
        t.setToIdentity();
        t.translate(0.0f, 15.0f);
        for (int i = 0; i < 8; i++) {
            e1.transform(t);
            border.add(e1);
            e2.transform(t);
            border.add(e2);
        }

        // do the ellipse on the bottom
        ee = new Ellipse2D.Float(0.0f, 0.0f, 15.0f, 15.0f);
        e = new Area(ee);
        t.setToIdentity();
        t.translate(100.0 - 15.0 / 2.0, 137.5 - 15.0 / 2.0);
        e.transform(t);
        t.setToIdentity();
        t.translate(-95.0, 0.0);
        e.transform(t);

        int iterations = 16;
        double theta = Math.PI;
        double thetaIncr = Math.PI / (double) iterations;
        double majorAxis = 105.0;
        double minorAxis = 95.0;
        double lastx = minorAxis * Math.cos(theta);
        double lasty = majorAxis * Math.sin(theta);
        double thisx;
        double thisy;
        double deltax;
        double deltay;

        for (int i = 0; i < iterations; i++) {
            border.add(e);
            theta += thetaIncr;
            thisx = minorAxis * Math.cos(theta);
            thisy = majorAxis * Math.sin(theta);
            deltax = thisx - lastx;
            deltay = -(thisy - lasty);
            lastx = thisx;
            lasty = thisy;
            t.setToIdentity();
            t.translate(deltax, deltay);
            e.transform(t);
        }

        paintBorder(g, border);
    }
}
