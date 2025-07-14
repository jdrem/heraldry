package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;
import org.apache.batik.ext.awt.geom.ShapeExtender;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class Gyron implements Drawable {
    final private Tincture tincture;

    public Gyron(Tincture tincture) {
        this.tincture = tincture;
    }

    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Path2D path = new Path2D.Double();
        path.moveTo(0.0, 0.0);
        path.lineTo(100.0, 125.0);
        path.lineTo(0.0, 125.0);
        path.lineTo(0.0, 0.0);
        Area gyron = new Area(path);
        gyron.intersect(new Area(Shield.shieldShape));
        if (!affineTransform.isIdentity())
            gyron.transform(affineTransform);
        tincture.fill(graphics, gyron);
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }
}
