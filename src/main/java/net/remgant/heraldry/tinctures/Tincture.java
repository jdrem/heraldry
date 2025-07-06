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

public abstract class Tincture {
    public abstract void fill(Graphics2D graphics, Area area);
    public abstract void draw(Graphics2D graphics2D, Area area);
    public abstract boolean isMetal();
    public abstract boolean isColor();
    public abstract boolean isFur();
    public Tincture darker() {
        throw new UnsupportedOperationException();
    }
    public Tincture lighter() {
        throw new UnsupportedOperationException();
    }
    final public static Tincture ARGENT = new Metal(java.awt.Color.WHITE);
    final public static Tincture OR = new Metal(java.awt.Color.YELLOW);
    final public static Tincture GULES = new Color(java.awt.Color.RED);
    final public static Tincture AZURE = new Color(java.awt.Color.BLUE);
    final public static Tincture SABLE = new Color(java.awt.Color.BLACK);
    final public static Tincture VERT = new Color(java.awt.Color.GREEN);
    final public static Tincture PURPURE = new Color(new java.awt.Color(128, 0, 128));
    final public static Tincture ERMINE = new Ermine(Tincture.ARGENT, Tincture.SABLE);
    final public static Tincture ERMINES = new Ermine(Tincture.SABLE, Tincture.ARGENT);
    final public static Tincture ERMINOIS= new Ermine(Tincture.OR, Tincture.SABLE);
    final public static Tincture PEAN = new Ermine(Tincture.SABLE, Tincture.OR);
    final public static Tincture VAIR = new Vair(Tincture.ARGENT, Tincture.AZURE);

    @Override
    public String toString() {
        if (this instanceof Color) {
            Color c = (Color)this;
            if (c.color.equals(java.awt.Color.RED))
                return "GULES";
            if (c.color.equals(java.awt.Color.BLACK))
                return "SABLE";
            if (c.color.equals(java.awt.Color.BLUE))
                return "AZURE";
            if (c.color.equals(java.awt.Color.GREEN))
                return "VERT";
            return  "PURPURE";
        }
        if (this instanceof Metal) {
            Metal m = (Metal)this;
            if (m.color.equals(java.awt.Color.WHITE))
                return "ARGENT";
            return "OR";
        }
        if (this instanceof Vair) {
            Vair v = (Vair)this;
            if (v.firstTincture.equals(Tincture.ARGENT) && v.secondTincture.equals(Tincture.AZURE))
                return "VAIR";
            return String.format("VAIR of %s and %s", v.firstTincture, v.secondTincture);
        }
        Ermine e = (Ermine)this;
        if (e.spotColor.isColor() && e.fieldColor.isMetal()) {
            if (((Metal)e.fieldColor).color.equals(java.awt.Color.WHITE))
                return "ERMINE";
            return "ERMINOIS";
        }
        if (e.spotColor.isMetal() && e.fieldColor.isColor()) {
            if (((Metal)e.spotColor).color.equals(java.awt.Color.WHITE))
                return "ERMINES";
            return "PEAN";
        }
        throw new RuntimeException("unkown tincture: "+this.getClass().getName());
//        return "UNKOWN";
    }
}
