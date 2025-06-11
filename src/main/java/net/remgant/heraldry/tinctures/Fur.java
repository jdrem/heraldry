package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Fur extends Tincture {

    @Override
    public boolean isMetal() {
        return false;
    }

    @Override
    public boolean isColor() {
        return false;
    }

    @Override
    public boolean isFur() {
        return true;
    }

    @Override
    public void draw(Graphics2D graphics2D, Area area) {
        throw new UnsupportedOperationException();
    }
}
