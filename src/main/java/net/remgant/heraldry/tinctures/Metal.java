package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public class Metal extends Tincture {
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
}
