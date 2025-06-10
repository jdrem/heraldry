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
        graphics.fill(area);
        Rectangle2D bounds = area.getBounds2D();
        int columns = (int)(bounds.getWidth() / 30.0) + 1;
        int rows = (int)(bounds.getHeight() / 30.0) + 1;

        for (int j=0; j<rows; j++) {
            for (int i=0; i<columns; i++) {
                double x = i * 60.0 + (j % 2 == 1 ?  30.0 : 0.0);
                double y = j * 30;
                ermineSpot.draw(graphics, AffineTransform.getTranslateInstance(x, y));
            }
        }
    }
}
