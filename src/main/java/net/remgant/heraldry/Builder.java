package net.remgant.heraldry;

import net.remgant.geom.Line;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Builder {
    final private Graphics2D graphics;
    final private List<Drawable> list;
    public Builder(Graphics2D graphics) {
        this.graphics = graphics;
        list = new ArrayList<>();
    }

    Map<String, Color> colorMap = Map.of(
            "or", Color.YELLOW,
            "argent", Color.WHITE,
            "gules", Color.RED,
            "azure", Color.BLUE,
            "sable", Color.BLACK,
            "vert", Color.GREEN,
            "purpure", Color.MAGENTA
    );
    public Builder fieldOf(String color) {
        list.add(new Shield(colorMap.get(color)));
        return this;
    }

    public Builder perPale(String color1, String color2) {
        return this;
    }

    public Builder perFess(String color1, String color2) {
        return this;
    }

    public Builder fess(String color) {
        list.add(new Fess(colorMap.get(color)));
        return this;
    }

    public Builder fess(String color, LineType lineType) {
        return this;
    }

    public Builder pale(String color) {
        list.add(new Pale(colorMap.get(color)));
        return this;
    }

    public Builder chief(String color) {
        list.add(new Chief(colorMap.get(color)));
        return this;
    }

    public Builder cross(String color) {
        list.add(new Cross(colorMap.get(color)));
        return this;
    }

    public Builder bend(String color) {
        list.add(new Bend(colorMap.get(color)));
        return this;
    }

    public Builder bendSinister(String color) {
        list.add(new BendSinister(colorMap.get(color)));
        return this;
    }

    public Builder saltire(String color) {
        list.add(new Saltire(colorMap.get(color)));
        return this;
    }

    public Builder chevron(String color) {
        list.add(new Chevron(colorMap.get(color)));
        return this;
    }

    public Builder border(String color) {
        return this;
    }

    public Builder border(String color, LineType lineType) {
        return this;
    }

    public void build(Consumer<Graphics2D> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics);
        }
        consumer.accept(graphics);
    }
}
