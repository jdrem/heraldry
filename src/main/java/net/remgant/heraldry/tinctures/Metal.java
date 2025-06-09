package net.remgant.heraldry.tinctures;

public abstract class Metal extends Tincture {
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
