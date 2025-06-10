package net.remgant.heraldry.tinctures;

import net.remgant.heraldry.ErmineSpot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Ermine extends Fur {
    Tincture spotColor;
    Tincture fieldColor;

    public Ermine(Tincture fieldColor, Tincture spotColor) {
        this.fieldColor = fieldColor;
        this.spotColor = spotColor;
    }

    @Override
    public void fill(Graphics2D graphics, Area area) {
        ErmineSpot ermineSpot = new ErmineSpot(spotColor, null, 1.0);
        fieldColor.fill(graphics, area);
        Rectangle2D ermineBounds = ermineSpot.getBounds();
        double size = Math.max(ermineBounds.getWidth(), ermineBounds.getHeight()) * 0.67;
        Rectangle2D bounds = area.getBounds2D();
        int columns = (int)(bounds.getWidth() / size) + 1;
        int rows = (int)(bounds.getHeight() / size) + 1;

        for (int j=0; j<rows; j++) {
            for (int i=0; i<columns; i++) {
                double x = i * 2.0 * size + (j % 2 == 1 ?  size : 0.0);
                double y = j * 2.0 * size;
                AffineTransform at = new AffineTransform();
                at.concatenate(AffineTransform.getScaleInstance(0.67, 0.67));
                at.concatenate(AffineTransform.getTranslateInstance(x,y));
                ermineSpot.draw(graphics, at);
            }
        }
    }
}
