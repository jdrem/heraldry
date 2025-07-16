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

public interface Tincture {
    void fill(Graphics2D graphics, Area area);
    void draw(Graphics2D graphics2D, Area area);
    boolean isMetal();
    boolean isColor();
    boolean isFur();
    default Tincture darker() {
        throw new UnsupportedOperationException();
    }
    default Tincture lighter() {
        throw new UnsupportedOperationException();
    }

    Tincture ARGENT = new Metal(java.awt.Color.WHITE);
    Tincture OR = new Metal(java.awt.Color.YELLOW);
    Tincture GULES = new Color(java.awt.Color.RED);
    Tincture AZURE = new Color(java.awt.Color.BLUE);
    Tincture SABLE = new Color(java.awt.Color.BLACK);
    Tincture VERT = new Color(java.awt.Color.GREEN);
    Tincture PURPURE = new Color(new java.awt.Color(128, 0, 128));
    Tincture ERMINE = new Ermine(Tincture.ARGENT, Tincture.SABLE);
    Tincture ERMINES = new Ermine(Tincture.SABLE, Tincture.ARGENT);
    Tincture ERMINOIS = new Ermine(Tincture.OR, Tincture.SABLE);
    Tincture PEAN = new Ermine(Tincture.SABLE, Tincture.OR);
    Tincture VAIR = new Vair(Tincture.ARGENT, Tincture.AZURE);
}
