package net.remgant.heraldry;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class FleurDeLis implements Drawable, java.io.Serializable {

    protected Color color;
    protected double xpos;
    protected double ypos;
    protected double scale;

    public FleurDeLis(Color c, double xpos, double ypos, double scale) {
        this.color = c;
        this.xpos = xpos;
        this.ypos = ypos;
        this.scale = scale;
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }

    public void draw(Graphics2D g) {
        Ellipse2D.Float e1 = new Ellipse2D.Float(-25.0f, -10.0f, 20.0f, 20.0f);
        Ellipse2D.Float e2 = new Ellipse2D.Float(-30.0f, -5.0f, 20.0f, 20.0f);

        Area f1 = new Area();
        f1.add(new Area(e1));
        f1.subtract(new Area(e2));
        Area fleur = new Area();
        fleur.add(f1);
        e1 = new Ellipse2D.Float(5.0f, -10.0f, 20.0f, 20.0f);
        e2 = new Ellipse2D.Float(10.0f, -5.0f, 20.0f, 20.0f);
        f1 = new Area();
        f1.add(new Area(e1));
        f1.subtract(new Area(e2));
        fleur.add(f1);

        e1 = new Ellipse2D.Float(-5.0f, -27.5f, 10.0f, 40.0f);
        fleur.add(new Area(e1));

        // e1 = new Ellipse2D.Float(-10.0f,-0.0f,20.0f,5.0f);
        // fleur.add(new Area(e1));
        RoundRectangle2D.Float rr = new RoundRectangle2D.Float
                (-15.0f, -0.0f, 30.0f, 5.0f, 2.0f, 2.0f);
        fleur.add(new Area(rr));

        double x = 200.0 * xpos;
        double y = 255.0 * ypos;
        AffineTransform t = new AffineTransform();
        t.translate(x, y);
        fleur.transform(t);

        g.setColor(color);
        g.fill(fleur);
    }
}
