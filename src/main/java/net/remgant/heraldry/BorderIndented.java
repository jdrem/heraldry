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

class BorderIndented extends Border {

    public BorderIndented(Tincture tincture) {
        super(tincture);
    }

    public BorderIndented(Tincture[] t, int division) {
        super(t, division);
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Area border = new Area();
        Rectangle2D.Float outerR =
                new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f);
        Ellipse2D.Float outerC =
                new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f);
        border.add(new Area(outerR));
        border.add(new Area(outerC));

        Rectangle2D.Float innerR =
                new Rectangle2D.Float(10.0f, 10.0f, 180.0f, 135.0f);
        Ellipse2D.Float innerC =
                new Ellipse2D.Float(10.0f, 50.0f, 180.0f, 190.0f);
        border.subtract(new Area(innerR));
        border.subtract(new Area(innerC));

        Area d = new Area(new Rectangle2D.Float(-6.36f, -6.36f, 12.72f, 12.72f));
        AffineTransform t = new AffineTransform();
        t.rotate(Math.PI / 4.0);
        d.transform(t);
        // Indent the top edge of the border

        Area dd = (Area) d.clone();
        t.setToIdentity();
        t.translate(11.0, 10.0);
        dd.transform(t);
        t.setToIdentity();
        t.translate(18.0, 0.0);
        for (int i = 0; i < 11; i++) {
            border.add(dd);
            dd.transform(t);
        }

        // do both sides
        Area d1 = (Area) d.clone();
        Area d2 = (Area) d.clone();
        t.setToIdentity();
        t.translate(11.0, 28.0);
        d1.transform(t);
        t.setToIdentity();
        t.translate(189.0, 28.0);
        d2.transform(t);
        t.setToIdentity();
        t.translate(0.0, 18.0);
        for (int i = 0; i < 8; i++) {
            border.add(d1);
            d1.transform(t);
            border.add(d2);
            d2.transform(t);
        }

        // do ellipse at the bottom
        t.setToIdentity();
        t.translate(100.0 - 15.0 / 2.0, 137.5 - 15.0 / 2.0);
        t.translate(-85.0, 0.0);

        int iterations = 18;
        double theta = Math.PI;
        double thetaIncr = Math.PI / (double) iterations;
        double majorAxis = 109.0;
        double minorAxis = 93.0;
        double lastx = minorAxis * Math.cos(theta);
        double lasty = majorAxis * Math.sin(theta);
        double thisx;
        double thisy;
        double deltax;
        double deltay;

        AffineTransform rot = new AffineTransform();
        for (int i = 0; i < iterations; i++) {
            dd = (Area) d.clone();
            rot.rotate(-Math.PI / (double) iterations);
            dd.transform(rot);
            theta += thetaIncr;
            thisx = minorAxis * Math.cos(theta);
            thisy = majorAxis * Math.sin(theta);
            deltax = thisx - lastx;
            deltay = -(thisy - lasty);
            lastx = thisx;
            lasty = thisy;
            t.translate(deltax, deltay);
            dd.transform(t);
            if (i >= 1 && i <= 15)
                border.add(dd);
        }

        paintBorder(g, border);
    }
}
