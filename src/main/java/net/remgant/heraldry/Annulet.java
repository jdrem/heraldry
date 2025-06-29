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
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Annulet extends Charge {
    protected Annulet(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    final static Shape shape;
    static {
       Area e1 = new Area(new Ellipse2D.Float(-15.0f, -15.0f, 30.0f, 30.0f));
       Area e2 = new Area(new Ellipse2D.Float(-10.0f, -10.0f, 20.0f, 20.0f));
       e1.subtract(e2);
       shape = e1;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public String defArticle() {
        return "an";
    }
}
