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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class ErmineSpot extends Charge {
    public ErmineSpot(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static Shape shape;
    static {
        Area e1 = new Area(new Ellipse2D.Float(-1.5f, 0.0f, 1.0f, 1.0f));
        Area e2 = new Area(new Ellipse2D.Float(-0.5f, -1.0f, 1.0f, 1.0f));
        Area e3 = new Area(new Ellipse2D.Float(0.5f, 0.0f, 1.0f, 1.0f));
        Area area = new Area();
        area.add(e1);
        area.add(e2);
        area.add(e3);
        Path2D.Float p1 = new Path2D.Float();
        p1.moveTo(0.0, 0.0);
        p1.lineTo(-1.5, 5.0);
        p1.lineTo(1.5, 5.0);
        p1.lineTo(0.0, 0.0);
        area.add(new Area(p1));
        Path2D.Float p2 = new Path2D.Float();
        p2.moveTo(0.0, 3.0);
        p2.lineTo(-1.5, 5.0);
        p2.lineTo(1.5, 5.0);
        p2.lineTo(0.0, 3.0);
        area.subtract(new Area(p2));
        double d = Math.max(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
        double scale = 30.0 / d;
        area.transform(AffineTransform.getScaleInstance(scale, scale));
        d = area.getBounds2D().getHeight() / 2.0;
        d = d + area.getBounds2D().getY();
        area.transform(AffineTransform.getTranslateInstance(0.0, -d));
        shape = area;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
