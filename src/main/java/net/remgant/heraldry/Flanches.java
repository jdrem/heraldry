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

public class Flanches implements Drawable {
    final private Tincture tincture;

    public Flanches(Tincture tincture) {
        this.tincture = tincture;
    }
    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Area arc1 = new Area(new Arc2D.Float(new Rectangle2D.Float(-50.0f, 0.0f, 100, 300.0f), 270.0f, 180.0f, Arc2D.CHORD));
        Area arc2 = new Area(new Arc2D.Float(new Rectangle2D.Float(150.0f, 0.0f, 100, 300.0f), 90.0f, 180.0f, Arc2D.CHORD));
        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));

        Area flanches = new Area();
        flanches.add(arc1);
        flanches.add(arc2);
        flanches.intersect(shield);

        tincture.fill(graphics, flanches);
    }
}
