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

class FessInvected implements Drawable {
    Tincture tincture;

    public FessInvected(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area fess = new Area();
        float top = 95.0f;
        float bot = 145.0f;
        fess.add(new Area(new Rectangle2D.Float(0.0f, top, 200.0f, bot - top)));

        Ellipse2D.Float e = new Ellipse2D.Float();
        for (int i = 0; i < 10; i++) {
            e.setFrame((float) i * 20.0f, top - 10.0f, 20.0f, 20.0f);
            fess.add(new Area(e));
            e.setFrame((float) i * 20.0f, bot - 10.0f, 20.0f, 20.0f);
            fess.add(new Area(e));
        }

        tincture.fill(g, fess);
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }
}

