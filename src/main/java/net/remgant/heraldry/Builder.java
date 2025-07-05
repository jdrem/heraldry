/*
  Copyright 2025 Jeffrey D. Remillard <jdr@remgant.net>

  This file is part of the Remgant Heraldry Library hosted at https://github.com/jdrem/heraldry.

  The Remgant Heraldry Library is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
  License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
  later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program. If not,
  see <https://www.gnu.org/licenses/>.
 */
package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Builder {
    final private Graphics2D graphics;
    final private FileWriter fileWriter;
    final private List<Drawable> list;
    final private List<String> description;

    public Builder(FileWriter fileWriter) {
        this.graphics = fileWriter.createGraphics();
        this.fileWriter = fileWriter;
        this.list = new ArrayList<>();
        this.description = new ArrayList<>();
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
        this.description = new ArrayList<>();
    }

    public Builder fieldOf(Tincture tincture) {
        list.add(new Shield(tincture));
        description.add(tincture.toString());
        return this;
    }

    public Builder perPale(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_PALE));
        description.add(String.format("per pale %s and %s",color1.toString(), color2.toString()));
        return this;
    }

    public Builder perFess(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_FESS));
        description.add(String.format("per fess %s and %s",color1.toString(), color2.toString()));

        return this;
    }

    public Builder perBend(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_BEND));
        description.add(String.format("per bend %s and %s",color1.toString(), color2.toString()));

        return this;
    }

    public Builder perBendSinister(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_BEND_SINISTER));
        description.add(String.format("per bend sinister %s and %s",color1.toString(), color2.toString()));
        return this;
    }


    public Builder perChevron(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_CHEVRON));
        description.add(String.format("per chevron %s and %s",color1.toString(), color2.toString()));
        return this;
    }

    public Builder perSaltire(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_SALTIRE));
        description.add(String.format("per saltire %s and %s",color1.toString(), color2.toString()));

        return this;
    }

    public Builder perCross(Tincture color1, Tincture color2) {
        list.add(new Shield(color1, color2, Shield.LineOfDivision.PER_CROSS));
        description.add(String.format("per cross %s and %s",color1.toString(), color2.toString()));
        return this;
    }

    public Builder fess(Tincture tincture) {
        list.add(new Fess(tincture));
        description.add(String.format("a fess %s", tincture));
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
        description.add(String.format("a pale %s", tincture));

        return this;
    }

    public Builder chief(Tincture tincture) {
        list.add(new Chief(tincture));
        description.add(String.format("a chief %s", tincture));

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
        Drawable thisDrawable = function.apply(tincture);
        list.add(thisDrawable);
        description.add(String.format("%s %s", thisDrawable.defArticle(), thisDrawable.description()));
        return this;
    }

    public Builder add(Drawable drawable) {
        list.add(drawable);
        if (drawable instanceof Shield)
            description.add(drawable.description());
        else
            description.add(String.format("%s %s", drawable.defArticle(), drawable.description()));
        return this;
    }

    public void build(BiConsumer<Graphics2D, String> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics);
        }
        consumer.accept(graphics, "");
    }

    public void build(Consumer<FileWriter> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics);
        }
        consumer.accept(fileWriter);
    }

    public void build(AffineTransform affineTransform, Consumer<FileWriter> consumer) {
        for (Drawable drawable : list) {
            drawable.draw(graphics, affineTransform);
        }
        consumer.accept(fileWriter);
    }


    public Builder add(MultiFunction<Tincture, Shield.Position, Double, Drawable> function, Tincture tincture, Shield.Position position, double scale) {
        Drawable thisDrawable = function.apply(tincture, position, scale);
        list.add(thisDrawable);
        description.add(String.format("%s %s %s", thisDrawable.defArticle(), thisDrawable.name(), tincture.toString().toLowerCase()));
        return this;
    }

    String[] numberAsText = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight"};
    public Builder add(MultiFunction<Tincture, Shield.Position, Double, Drawable> function, Tincture tincture, Shield.ArrangementOfCharges arrangementOfCharges) {
        for (Shield.Position position : arrangementOfCharges.getPositions()) {
            list.add(function.apply(tincture, position, 1.0));
        }
        if (arrangementOfCharges.getPositions().length <= 8) {
            description.add(String.format("%s %s %s",
                    numberAsText[arrangementOfCharges.getPositions().length],
                    function.apply(tincture, Shield.Position.CENTER, 1.0).namePlural(),
                    tincture.toString().toLowerCase()));
        } else {
            description.add(String.format("%d %s %s",
                    arrangementOfCharges.getPositions().length,
                    function.apply(tincture, Shield.Position.CENTER, 1.0).namePlural(),
                    tincture.toString().toLowerCase()));
        }
        return this;
    }

    public String getDescription() {
        return String.join(", ", description);
    }

    public void reset() {
        this.list.clear();
        this.description.clear();
    }
}
