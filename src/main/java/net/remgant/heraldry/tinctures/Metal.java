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
package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public class Metal implements Tincture {
    java.awt.Color color;

    Metal(java.awt.Color color) {
        this.color = color;
    }

    @Override
    public void fill(Graphics2D graphics, Area area) {
        graphics.setColor(color);
        graphics.fill(area);
    }

    @Override
    public void draw(Graphics2D graphics, Area area) {
        graphics.setColor(color);
        graphics.draw(area);
    }

    @Override
    public boolean isMetal() {
        return true;
    }

    @Override
    public boolean isColor() {
        return false;
    }

    @Override
    public boolean isFur() {
        return false;
    }
    @Override
    public Tincture darker() {
        return new Color(this.color.darker());
    }

    @Override
    public Tincture lighter() {
        return new Color(this.color.darker());
    }

    @Override
    public String toString() {
        if (color.equals(java.awt.Color.WHITE))
            return "ARGENT";
        return "OR";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        return ((Metal)obj).color.equals(this.color);
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }
}
