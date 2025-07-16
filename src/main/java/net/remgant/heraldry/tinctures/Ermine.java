/*
  Copyright 2025 Jeffrey D. Remillard <jdr@remgant.net>

  This file is part of the Remgant Heraldry Library hosted at https://github.com/jdrem/heraldry.

  The Remgant Heraldry Library is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
  License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
  later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program. If not,
  see <https://www.gnu.org/licenses/>.
 */
package net.remgant.heraldry.tinctures;

import net.remgant.heraldry.ErmineSpot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

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
        Area spotArea = new Area();
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
                Area spot = new Area(ermineSpot.getShape());
                spot.transform(at);
                spotArea.add(spot);
            }
        }
        if (bounds.getX() != 0.0 || bounds.getY() != 0.0) {
            AffineTransform at = AffineTransform.getTranslateInstance(bounds.getX(), bounds.getY());
            spotArea.transform(at);
        }
        spotArea.intersect(area);
        spotColor.fill(graphics, spotArea);
    }

    @Override
    public String toString() {
        if (spotColor.equals(SABLE) && fieldColor.equals(ARGENT))
            return "ERMINE";
        if (spotColor.equals(ARGENT) && fieldColor.equals(SABLE))
            return "ERMINES";
        if (spotColor.equals(SABLE) && fieldColor.equals(OR))
            return "ERMINOIS";
        if (spotColor.equals(OR) && fieldColor.equals(SABLE))
            return "PEAN";
        return String.format("%s ermined %s", fieldColor, spotColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash("ERMINE", fieldColor, spotColor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Ermine ermine = (Ermine)obj;
        return Objects.equals(fieldColor, ermine.fieldColor) && Objects.equals(spotColor, ermine.spotColor);
    }
}
