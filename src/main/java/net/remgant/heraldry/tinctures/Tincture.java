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

}
