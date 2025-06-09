package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public class Sable extends Color {
    @Override
    public void fill(Graphics2D graphics, Area area) {
        graphics.setColor(java.awt.Color.BLACK);
        graphics.fill(area);
    }
}
