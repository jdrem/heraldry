package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Vair extends Fur {
    final Tincture firstTincture;
    final Tincture secondTincture;

    public Vair(Tincture firstTincture, Tincture secondTincture) {
        this.firstTincture = firstTincture;
        this.secondTincture = secondTincture;
    }

    @Override
    public void fill(Graphics2D graphics, Area area) {
        firstTincture.fill(graphics, area);
        Rectangle2D bounds = area.getBounds2D();
        double width = bounds.getWidth() / 4.0;
        double height = width;
        Area vairArea = new Area();
        Path2D path = new Path2D.Double();
        path.moveTo(0.0, height);
        path.lineTo(width, height);
        path.lineTo(width * 3.0 / 4.0, height * 3.0 / 4.0);
        path.lineTo(width * 3.0 / 4.0, height / 4.0);
        path.lineTo(width /2.0, 0.0);
        path.lineTo(width / 4.0, height / 4.0);
        path.lineTo(width / 4.0, height * 3.0 / 4.0);
        path.lineTo(0.0, height);
        for (int i=0; i<8; i++) {
            for (int j=0; j < 8; j++) {
                AffineTransform at = AffineTransform.getTranslateInstance((double)i * width, (double)j * height);
                Area a = new Area(path);
                if (j % 2 != 0)
                    a.transform(AffineTransform.getTranslateInstance(-width/2.0, 0.0));
                a.transform(at);
                vairArea.add(a);
            }
        }
        if (bounds.getX() != 0.0 || bounds.getY() != 0.0) {
            AffineTransform at = AffineTransform.getTranslateInstance(bounds.getX(), bounds.getY());
            vairArea.transform(at);
        }
        vairArea.intersect(area);
        secondTincture.fill(graphics, vairArea);
    }

    @Override
    public String toString() {
        if (firstTincture.equals(Tincture.ARGENT) && secondTincture.equals(Tincture.AZURE))
            return "VAIR";
        return String.format("VAIR of %s and %s", firstTincture, secondTincture);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vair vair = (Vair) o;
        return Objects.equals(firstTincture, vair.firstTincture) && Objects.equals(secondTincture, vair.secondTincture);
    }

    @Override
    public int hashCode() {
        return Objects.hash("VAIR", firstTincture, secondTincture);
    }
}
