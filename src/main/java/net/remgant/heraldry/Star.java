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

class Star extends Charge {

    final static Shape starShape;
    static {
        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(-0.723f, 0.0f);
        path.lineTo(0.0f, -1.0f);
        path.lineTo(0.723f, 0.0f);
        path.lineTo(0.0f, 0.0f);
        Area arm = new Area(path);
        Area star = new Area();
        for (int i = 0; i < 5; i++) {
            star.add(arm);
            arm.transform(AffineTransform.getRotateInstance(2.0 * Math.PI / 5.0));
        }
        star.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        starShape = star;
    }

    @Override
    public Shape getShape() {
        return starShape;
    }

    public Star(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area star = new Area(starShape);
        Area shield = new Area(Shield.shieldShape);
        if (!affineTransform.isIdentity())
            star.transform(affineTransform);
        if (scale != 1.0) {
            star.transform(AffineTransform.getScaleInstance(scale, scale));
        }
        double x = position.x() * shield.getBounds2D().getWidth();
        double y = position.y() * shield.getBounds2D().getHeight();

        star.transform(AffineTransform.getTranslateInstance(x, y));
        tincture.fill(g, star);
    }
}
