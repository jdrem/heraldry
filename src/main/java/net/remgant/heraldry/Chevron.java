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

public class Chevron implements Drawable {
    Tincture tincture;

    public Chevron(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area chevron = new Area();

        Path2D.Float path = new Path2D.Float();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(-139.0f, 250.0);
        path.lineTo(130.0f, 250.0);
        path.lineTo(0.0f, 0.0f);
        chevron.add(new Area(path));
        path.transform(AffineTransform.getTranslateInstance(0.0, 110.0f));
        chevron.subtract(new Area(path));
        chevron.transform(AffineTransform.getTranslateInstance(100.0f, 20.0f));

        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        chevron.intersect(shield);
        if (!affineTransform.isIdentity())
            chevron.transform(affineTransform);
        tincture.fill(g, chevron);
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }
}
