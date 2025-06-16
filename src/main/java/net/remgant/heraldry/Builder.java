package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Builder {
    final private Graphics2D graphics;
    final private FileWriter fileWriter;
    final private List<Drawable> list;

    public Builder(FileWriter fileWriter) {
        this.graphics = fileWriter.createGraphics();
        this.fileWriter = fileWriter;
        this.list = new ArrayList<>();
    }

    public Builder(Graphics2D graphics) {
        this.graphics = graphics;
        this.fileWriter = new FileWriter() {
            @Override
            public Graphics2D createGraphics() {
                return graphics;
            }

            @Override
            public void writeToFile(String fileName) throws IOException {

            }
        };
        list = new ArrayList<>();
    }

    public Builder fieldOf(Tincture tincture) {
        list.add(new Shield(tincture));
        return this;
    }

    public Builder perPale(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_PALE));
        return this;
    }

    public Builder perFess(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_FESS));
        return this;
    }

    public Builder perBend(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_BEND));
        return this;
    }

    public Builder perBendSinister(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_BEND_SINISTER));
        return this;
    }


    public Builder perChevron(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_CHEVRON));
        return this;
    }

    public Builder perSaltire(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_SALTIRE));
        return this;
    }

    public Builder perCross(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_CROSS));
        return this;
    }

    public Builder fess(Tincture tincture) {
        list.add(new Fess(tincture));
        return this;
    }

    public Builder fess(Tincture tincture, Shield.VariationOfLine variationOfLine) {
        switch(variationOfLine) {
            case INVECTED -> list.add(new FessInvected(tincture));
            case ENGRAILED -> list.add(new FessEngrailed(tincture));
            case INDENTED -> list.add(new FessIndented(tincture));
            default -> list.add(new Fess(tincture));
        }
        return this;
    }



    public Builder pale(Tincture tincture) {
        list.add(new Pale(tincture));
        return this;
    }

    public Builder chief(Tincture tincture) {
        list.add(new Chief(tincture));
        return this;
    }

    public Builder cross(Tincture tincture) {
        list.add(new Cross(tincture));
        return this;
    }

    public Builder bend(Tincture tincture) {
        list.add(new Bend(tincture));
        return this;
    }

    public Builder bendSinister(Tincture tincture) {
        list.add(new BendSinister(tincture));
        return this;
    }

    public Builder saltire(Tincture tincture) {
        list.add(new Saltire(tincture));
        return this;
    }

    public Builder chevron(Tincture tincture) {
        list.add(new Chevron(tincture));
        return this;
    }

    public Builder border(Tincture tincture) {
        list.add(new Border(tincture));
        return this;
    }

    public Builder border(Tincture tincture, Shield.VariationOfLine variationOfLine) {
        switch(variationOfLine) {
            case INVECTED -> list.add(new BorderInvected(tincture));
            case ENGRAILED -> list.add(new BorderEngrailed(tincture));
            case INDENTED -> list.add(new BorderIndented(tincture));
            default -> list.add(new Border(tincture));
        }
        return this;
    }

    public Builder add(Function<Tincture,Drawable> function, Tincture tincture) {
        list.add(function.apply(tincture));
        return this;
    }

    public Builder add(Drawable drawable) {
        list.add(drawable);
        return this;
    }

    public void build(Consumer<FileWriter> consumer, boolean b) {
        for (Drawable drawable : list) {
            drawable.draw(graphics);
        }
        consumer.accept(fileWriter);
    }

    public void build(Consumer<Graphics2D> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics);
        }
        consumer.accept(graphics);
    }

    public void build(AffineTransform affineTransform, Consumer<Graphics2D> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics, affineTransform);
        }
        consumer.accept(graphics);
    }

    public Builder add(DrawRandom.MultiFunction<Tincture, Shield.Position, Double, Drawable> function, Tincture tincture, Shield.Position position, double scale) {
        list.add(function.apply(tincture, position, scale));
        return this;
    }
}
