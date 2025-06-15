package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

class Border implements Drawable, java.io.Serializable {

    final public static int DIV_NONE = 0;
    final public static int DIV_PER_PALE = 1;
    final public static int DIV_PER_FESS = 2;
    final public static int DIV_QUARTERLY = 3;
    final public static int DIV_GYRONNY = 4;

    protected Color[] color;
    protected Tincture[] tinctures;
    protected int division;

    public Border(Tincture tincture) {
        this.tinctures = new Tincture[]{tincture};
        division = DIV_NONE;
    }

    public Border(Tincture[] t, int division) {
        this.tinctures = t;
        this.division = division;
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
                new Rectangle2D.Float(15.0f, 15.0f, 170.0f, 125.0f);
        Ellipse2D.Float innerC =
                new Ellipse2D.Float(15.0f, 50.0f, 170.0f, 180.0f);
        border.subtract(new Area(innerR));
        border.subtract(new Area(innerC));
        paintBorder(g, border);
    }

    protected void paintBorder(Graphics2D g, Area border) {
        Area mask;
        Area b1;
        AffineTransform t = new AffineTransform();

        switch (division) {
            case DIV_PER_PALE:
                mask = new Area(new Rectangle2D.Float(100.0f, 0.0f, 100.0f, 255.0f));
                b1 = (Area) border.clone();
                b1.subtract(mask);
                tinctures[0].fill(g, b1);
                t.translate(-100.0f, 0.0f);
                mask.transform(t);
                b1 = (Area) border.clone();
                b1.subtract(mask);
                tinctures[1].fill(g, b1);
                break;
            case DIV_PER_FESS:
                mask = new Area(new Rectangle2D.Float(0.0f, 127.5f, 200.0f, 127.5f));
                b1 = (Area) border.clone();
                b1.subtract(mask);
                tinctures[0].fill(g, b1);
                t.translate(0.0f, -127.5f);
                mask.transform(t);
                b1 = (Area) border.clone();
                b1.subtract(mask);
                tinctures[1].fill(g, b1);
                break;
            case DIV_QUARTERLY:
                // upper right
                mask = new Area(new Rectangle2D.Float(100.0f, 0.0f, 100.0f, 127.5f));
                b1 = (Area) border.clone();
                b1.subtract(mask);
                // move to lower right
                t.translate(-100.0f, 127.5f);
                mask.transform(t);
                b1.subtract(mask);
                tinctures[0].fill(g, b1);
                t.setToIdentity();
                // move to upper left
                t.translate(0.0f, -127.5f);
                mask.transform(t);
                b1 = (Area) border.clone();
                b1.subtract(mask);
                t.setToIdentity();
                // move to lower right
                t.translate(100.0f, 127.5f);
                mask.transform(t);
                b1.subtract(mask);
                tinctures[0].fill(g, b1);
                break;
            case DIV_GYRONNY:
                // make a triangle
                GeneralPath path = new GeneralPath();
                path.moveTo(0.0f, 0.0f);
                path.lineTo(100.0f, 0.0f);
                path.lineTo(100.0f, 127.5f);
                path.lineTo(0.0f, 0.0f);
                Area tri1 = new Area(path);
                mask = new Area();
                mask.add(tri1);

                Area tri2 = (Area) tri1.clone();
                t.rotate(Math.PI);
                tri2.transform(t);
                t.setToIdentity();
                t.translate(200.0, 255.0);
                tri2.transform(t);
                mask.add(tri2);

                path = new GeneralPath();
                path.moveTo(0.0f, 0.0f);
                path.lineTo(100.0f, 0.0f);
                path.lineTo(100.0f, -127.5f);
                path.lineTo(0.0f, 0.0f);
                Area tri3 = new Area(path);
                Area tri4 = (Area) tri3.clone();
                t.setToIdentity();
                t.translate(100.0, 127.5);
                tri3.transform(t);
                mask.add(tri3);

                t.setToIdentity();
                t.rotate(Math.PI);
                tri4.transform(t);
                t.setToIdentity();
                t.translate(100.0, 127.5);
                tri4.transform(t);
                mask.add(tri4);

                b1 = (Area) border.clone();
                b1.intersect(mask);
                tinctures[0].fill(g, b1);
                // now flip the mask
                t.setToIdentity();
                t.setTransform(-1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
                mask.transform(t);
                t.setToIdentity();
                t.translate(200.0, 0.0);
                mask.transform(t);
                b1 = (Area) border.clone();
                b1.intersect(mask);
                tinctures[0].fill(g, b1);
                break;
            case DIV_NONE:
            default:
                tinctures[0].fill(g, border);
                break;
        }
    }

}
