package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Shield implements Drawable, java.io.Serializable {
    public enum Position {
        CHIEF_DEXTER(0.1667, 0.15),
        CHIEF_CENTER(0.5, 0.15),
        CHIEF_SINISTER(0.8333, 0.15),
        FESS_DEXTER(0.1667, 0.5),
        FESS_DEXTER_MID(0.25, 0.5),
        FESS_SINISTER_MID(0.74, 0.5),
        CENTER(0.5, 0.5),
        FESS_SINISTER(0.8333, 0.5),
        BEND_TOP_DEXTER(0.225, 0.1958),
        BEND_TOP_SINISTER(0.775, 0.1958),
        BEND_BOTTOM_SINISTER(0.775, 0.8),
        BEND_BOTTOM_DEXTER(0.225, 0.8),
        BASE(0.5, 0.8),
        PALE_TOP(0.5, 0.1958),
        PALE_BOTTOM(0.5, 0.8),
        PALE_TOP_MID(0.5, 0.25),
        PALE_BOTTOM_MID(0.5, 0.75),
        HONOR_POINT(0.5, 0.3),
        HONOR_POINT_DEXTER(0.25, 0.3),
        HONOR_POINT_SINISTER(0.75, 0.3),
        NAVEL_POINT(0.5, 0.7);
        double x;
        double y;

        Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
        double x() {return x;}
        double y() {return y;}
    }

    public enum LineOfDivision {
        NONE,
        PER_PALE,
        PER_FESS,
        PER_BEND,
        PER_BEND_SINISTER,
        PER_CHEVRON,
        PER_CROSS,
        PER_SALTIRE;
    }

    public static BufferedImage createImage() {
        // create an image
        BufferedImage image =
                new BufferedImage(200, 250, BufferedImage.TYPE_INT_ARGB);
        // get the graphics context
        Graphics2D g = image.createGraphics();

        // set the background to be transperant
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fill(new Rectangle2D.Float(0, 0, 200, 250));
        // unset transperancy
        g.setComposite(AlphaComposite.SrcOver);
        return image;
    }

    Tincture tincture;
    Tincture secondTincture;
    LineOfDivision lineOfDivision = LineOfDivision.NONE;
    public Shield(Tincture tincture) {
        this.tincture = tincture;
    }

    public Shield(Tincture tincture, Tincture secondTincture, LineOfDivision lineOfDivision) {
        this.tincture = tincture;
        this.secondTincture = secondTincture;
        this.lineOfDivision = lineOfDivision;
    }

    final public static Shape shieldShape;
    static {
        Area a = new Area();
        a.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        a.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        shieldShape = a;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Rectangle2D.Float rect = new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f);
        Ellipse2D.Float circ = new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f);
        Area area = new Area();
        area.add(new Area(rect));
        area.add(new Area(circ));
        area.transform(affineTransform);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fill(new Rectangle2D.Float(0, 0, (int)area.getBounds2D().getWidth(), (int)area.getBounds2D().getHeight()));
        // unset transperancy
        g.setComposite(AlphaComposite.SrcOver);
        if (lineOfDivision == LineOfDivision.PER_PALE) {
            Area mask = new Area(new Rectangle2D.Double(0.0, 0.0, area.getBounds2D().getWidth() / 2.0, area.getBounds2D().getHeight()));
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth() / 2.0, 0.0));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            tincture.fill(g, sinister);
            secondTincture.fill(g, dexter);
        } else if (lineOfDivision == LineOfDivision.PER_FESS) {
            Area mask = new Area(new Rectangle2D.Double(0.0, area.getBounds2D().getHeight() / 2.0, area.getBounds2D().getWidth(), area.getBounds2D().getHeight() / 2.0));
            Area top = new Area(area);
            top.subtract(mask);
            mask.transform(AffineTransform.getTranslateInstance(0.0, -area.getBounds2D().getHeight() / 2.0));
            Area bottom = new Area(area);
            bottom.subtract(mask);
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_BEND) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth(), 0.0);
            path.lineTo(0.0, 0.0);
            Area mask = new Area(path);
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getRotateInstance(Math.PI));
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            tincture.fill(g, dexter);
            secondTincture.fill(g, sinister);
        }else if (lineOfDivision == LineOfDivision.PER_BEND_SINISTER) {
            Path2D path = new Path2D.Double();
            path.moveTo(area.getBounds2D().getWidth(), 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth(), 0.0);
            Area mask = new Area(path);
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getRotateInstance(Math.PI));
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            tincture.fill(g, dexter);
            secondTincture.fill(g, sinister);
        } else if (lineOfDivision == LineOfDivision.PER_CHEVRON) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(0.0, 2.0 * area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth() / 2.0, area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth(), 2.0 * area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            Area mask = new Area(path);
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_SALTIRE) {
            Path2D path = new Path2D.Double();
            path.moveTo(area.getBounds2D().getWidth() / 2.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight() / 2.0);
            path.lineTo(0.0, area.getBounds2D().getHeight() / 2.0);
            path.lineTo(area.getBounds2D().getWidth() / 2.0, 0.0);
            Area mask = new Area();
            mask.add(new Area(path));
            path.transform(AffineTransform.getScaleInstance(1.0, -1.0));
            mask.add(new Area(path));
            mask.transform(AffineTransform.getTranslateInstance(0.0, area.getBounds2D().getHeight() / 2.0));
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_CROSS) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0,0.0);
            path.lineTo(area.getBounds2D().getWidth()/2.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(0.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(0.0, 0.0);
            path.moveTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()/2.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            Area mask = new Area();
            mask.add(new Area(path));
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else {
            tincture.fill(g, area);
        }
    }
}
