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

public class Heart extends Charge {
    protected Heart(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }
    final static protected Shape shape;
    static {
        Area area = new Area();
        Ellipse2D.Float e1 = new Ellipse2D.Float(-1.0f, -0.5f, 1.0f, 1.0f);
        Ellipse2D.Float e2 = new Ellipse2D.Float(0.0f, -0.5f, 1.0f, 1.0f);
        Rectangle2D.Float r = new Rectangle2D.Float(-1.0f, 0.0f, 2.0f, 1.0f);
        area.add(new Area(e1));
        area.add(new Area(e2));
        area.subtract(new Area(r));
        Path2D.Float path = new Path2D.Float();
        path.moveTo(-1.0f, 0.0);
        path.lineTo(1.0, 0.0);
        path.lineTo(0.0f, 1.0f);
        path.lineTo(-1.0f, 0.0f);
        area.add(new Area(path));
        area.transform(AffineTransform.getScaleInstance(20.0, 20.0));
        shape = area;
    }
    @Override
    public Shape getShape() {
        return shape;
    }
}
