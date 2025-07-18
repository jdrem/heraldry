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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface Drawable {
    void draw(Graphics2D graphics, AffineTransform affineTransform);
    Tincture getTincture();

    default void draw(Graphics2D graphics) {
        draw(graphics, new AffineTransform());
    }
    default void draw(java.awt.image.BufferedImage image) {
        draw(image.createGraphics(), new AffineTransform());
    }

    default String description() {
        return name() + " " + getTincture().toString().toLowerCase();
    }

    default String name() {
        String s = getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(" ");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        if (sb.charAt(0) == ' ' ) {
            return sb.substring(1);
        }
        return sb.toString();
    }

    default String namePlural() {
        return name() + "s";
    }

    default String defArticle() {
        return "a";
    }
}
