package net.remgant.heraldry.tinctures;

public abstract class Color extends Tincture {
    @Override
    public boolean isMetal() {
        return false;
    }

    @Override
    public boolean isColor() {
        return true;
    }

    @Override
    public boolean isFur() {
        return false;
    }
}
