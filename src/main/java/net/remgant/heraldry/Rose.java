package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.*;

public class Rose extends Charge {
    private final Tincture barbs;
    private final Tincture seeds;
    protected Rose(Tincture tincture, Tincture seeds, Tincture barbs, Shield.Position position, double scale) {
        super(tincture, position, scale);
        this.barbs = barbs;
        this.seeds = seeds;
    }

    private final static Shape petalsShape;
    private final static Shape seedShape;
    private final static Shape barbsShape;

    static {
        Area petals = new Area();
        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.0, 0.0);
        path.lineTo(-1.76, -3.0);
        path.lineTo(1.76, -3.0);
        path.lineTo(0.0, 0.0);
        Area petal = new Area(path);

       Area e1 = new Area(new Ellipse2D.Float(-1.76f, -1.76f/2.0f, 1.76f, 1.76f));
        Area e2 = new Area(new Ellipse2D.Float(0.0f, -1.76f/2.0f, 1.76f, 1.76f));
        Area r = new Area(new Rectangle2D.Float(-2.0f, 0.0f, 4.0f, 2.0f));
        e1.subtract(r);
        e2.subtract(r);
        e1.transform(AffineTransform.getTranslateInstance(0.0, -3.0));
        e2.transform(AffineTransform.getTranslateInstance(0.0, -3.0));
        petal.add(e1);
        petal.add(e2);
        for (int i=0; i<5; i++) {
            petals.add(petal);
            petal.transform(AffineTransform.getRotateInstance(2.0 * Math.PI / 5.0));
        }
        petals.transform(AffineTransform.getScaleInstance(3.75, 3.75));
        petalsShape = petals;

        Area seeds = new Area(new Ellipse2D.Float(-1.25f, -1.25f, 2.5f, 2.5f));
        seeds.transform(AffineTransform.getScaleInstance(3.75, 3.75));
        seedShape = seeds;

        path.reset();
        path.moveTo(-0.667, 0.0);
        path.lineTo(-0.667, -3.5);
        path.lineTo(0.0, -5.0);
        path.lineTo(0.667, -3.5);
        path.lineTo(0.667, 0.0);
        path.lineTo(-0.667, 0.0);
        Area barb = new Area(path);
        barb.transform(AffineTransform.getRotateInstance(2.0 * Math.PI / 10.0));
        Area barbs = new Area();
        for (int i=0; i<5; i++) {
            barbs.add(barb);
            barb.transform(AffineTransform.getRotateInstance(2.0 * Math.PI / 5.0));
        }
        barbs.transform(AffineTransform.getScaleInstance(3.75, 3.75));
        barbsShape = barbs;
    }


    @Override
    public Shape getShape() {
        Area a = new Area(petalsShape);
        a.add(new Area(barbsShape));
        return a;
    }

    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Area petalArea = new Area(petalsShape);
        Area seedArea = new Area(seedShape);
        Area barbsArea = new Area(barbsShape);
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity()) {
            petalArea.transform(affineTransform);
            seedArea.transform(affineTransform);
            barbsArea.transform(affineTransform);
        }
        if (scale != 1.0) {
            petalArea.transform(AffineTransform.getScaleInstance(scale, scale));
            seedArea.transform(AffineTransform.getScaleInstance(scale, scale));
            barbsArea.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = 0.0;
        double y = 0.0;
        if (position != null) {
            x = position.x() * shield.getBounds2D().getWidth();
            y = position.y() * shield.getBounds2D().getHeight();
        }

        barbsArea.transform(AffineTransform.getTranslateInstance(x, y));
        barbs.fill(graphics, barbsArea);

        petalArea.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(graphics, petalArea);

        seedArea.transform(AffineTransform.getTranslateInstance(x, y));
        seeds.fill(graphics, seedArea);
    }

    @Override
    public Rectangle2D getBounds() {
        Area a = new Area(petalsShape);
        a.add(new Area(barbsShape));
        return a.getBounds2D();
    }
}
