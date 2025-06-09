package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public class Argent extends Metal {
    @Override
    public void fill(Graphics2D graphics, Area area) {
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fill(area);
    }
}
