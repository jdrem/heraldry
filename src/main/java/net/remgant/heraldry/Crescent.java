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
import java.awt.geom.*;

class Crescent extends Charge {

    public Crescent(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static Shape crescentShape;
    static {
        Area crescent = new Area();
        Ellipse2D.Float c1 = new Ellipse2D.Float(-15.0f, -15.0f, 30.0f, 30.0f);
        Ellipse2D.Float c2 = new Ellipse2D.Float(-9.0f, -15.5f, 18.0f, 18.0f);
        crescent.add(new Area(c1));
        crescent.subtract(new Area(c2));
        crescentShape = crescent;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area crescent = new Area(crescentShape);
        Area shield = new Area(Shield.shieldShape);
        if (scale != 1.0) {
            crescent.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = position.x() * shield.getBounds2D().getWidth();
        double y = position.y() * shield.getBounds2D().getHeight();
        crescent.transform(AffineTransform.getTranslateInstance(x, y));
        if (!affineTransform.isIdentity())
            crescent.transform(affineTransform);
        tincture.fill(g, crescent);
    }

    @Override
    public Shape getShape() {
        return crescentShape;
    }
}
