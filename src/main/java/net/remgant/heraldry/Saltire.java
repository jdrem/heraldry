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
import java.awt.geom.Rectangle2D;

public class Saltire implements Drawable {
    Tincture tincture;

    public Saltire(Tincture tincture) {
        this.tincture = tincture;
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area saltire = new Area();
        Area a = new Area(new Rectangle2D.Float(0.0f, -30.0f, 300.0f, 60.0f));
        a.transform(AffineTransform.getRotateInstance(Math.atan2(Shield.shieldShape.getBounds().getHeight(), Shield.shieldShape.getBounds().getWidth())));
        saltire.add(a);

        Area b = new Area(new Rectangle2D.Float(0.0f, -30.0f, 300.0f, 60.0f));
        b.transform(AffineTransform.getScaleInstance(-1.0, 1.0));
        b.transform(AffineTransform.getRotateInstance(-Math.atan2(Shield.shieldShape.getBounds().getHeight(), Shield.shieldShape.getBounds().getWidth())));
        b.transform(AffineTransform.getTranslateInstance(200.0, 0.0));
        saltire.add(b);

        Area shield = new Area();
        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        saltire.intersect(shield);
        if (!affineTransform.isIdentity())
            saltire.transform(affineTransform);
        tincture.fill(g, saltire);
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }
}
