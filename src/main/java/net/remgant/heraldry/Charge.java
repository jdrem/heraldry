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
package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public abstract class Charge implements Drawable {
    protected Tincture tincture;
    protected Shield.Position position;
    protected double scale;

    public abstract Shape getShape();
    protected Charge(Tincture tincture, Shield.Position position, double scale) {
        this.tincture = tincture;
        this.position = position;
        this.scale = scale;
    }

    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Area area = new Area(getShape());
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity())
            area.transform(affineTransform);
        if (scale != 1.0) {
            area.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = 0.0;
        double y = 0.0;
        if (position != null) {
            x = position.x() * shield.getBounds2D().getWidth() * affineTransform.getScaleX();
            y = position.y() * shield.getBounds2D().getHeight() * affineTransform.getScaleY();
        }
        area.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(graphics, area);
    }

    public Rectangle2D getBounds() {
        if (scale == 1.0)
            return getShape().getBounds2D();
        Area a = new Area(getShape());
        a.transform(AffineTransform.getScaleInstance(scale, scale));
        return a.getBounds2D();
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }

//    @Override
//    public String description() {
//        return getClass().getSimpleName() + " " + tincture.toString();
//    }
}
