package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Shield implements Drawable, java.io.Serializable {
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

    public Shield(Tincture tincture) {
        this.tincture = tincture;
    }

    final public static Shape shieldShape;
    static {
        Area a = new Area();
        a.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        a.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        shieldShape = a;
    }

    public void draw(Graphics2D g) {
        Rectangle2D.Float rect = new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f);
        Ellipse2D.Float circ = new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f);
        Area area = new Area();
        area.add(new Area(rect));
        area.add(new Area(circ));

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fill(new Rectangle2D.Float(0, 0, 200, 250));
        // unset transperancy
        g.setComposite(AlphaComposite.SrcOver);
        tincture.fill(g, area);
    }
}
