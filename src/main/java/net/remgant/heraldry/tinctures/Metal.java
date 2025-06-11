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
    public boolean equals(Object obj) {
        if (!(obj instanceof Metal))
            return false;
        return ((Metal)obj).color.equals(this.color);
    }
}
