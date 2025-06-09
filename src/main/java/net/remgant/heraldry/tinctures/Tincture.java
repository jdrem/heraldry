package net.remgant.heraldry.tinctures;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Tincture {
    public abstract void fill(Graphics2D graphics, Area area);
    public abstract boolean isMetal();
    public abstract boolean isColor();
    public abstract boolean isFur();
    final public static Tincture ARGENT = new Argent();
    final public static Tincture SABLE = new Sable();
    final public static Tincture OR = new Or();
}
