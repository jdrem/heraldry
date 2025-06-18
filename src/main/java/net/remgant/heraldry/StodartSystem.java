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

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class StodartSystem {

    public static void main(String[] args) {
        FileWriterFactory fileWriterFactory = new FileWriterFactory("SVG", new Properties());

        FileWriter fileWriter = fileWriterFactory.getInstance();
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-01.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.OR)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-02.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.GULES)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-03.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.AZURE)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-04.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.VERT)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-05.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE, Shield.VariationOfLine.INVECTED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-06.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE, Shield.VariationOfLine.ENGRAILED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-07.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE, Shield.VariationOfLine.INDENTED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-08.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.OR, Shield.VariationOfLine.INVECTED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-09.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.OR, Shield.VariationOfLine.ENGRAILED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-10.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .border(Tincture.OR, Shield.VariationOfLine.INDENTED)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-11.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Crescent(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-12.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-13.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE, Shield.VariationOfLine.INVECTED)
                .border(Tincture.OR)
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-13a.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Border(new Tincture[]{Tincture.OR, Tincture.GULES}, Border.DIV_PER_PALE))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-14.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Border(new Tincture[]{Tincture.OR, Tincture.AZURE}, Border.DIV_PER_PALE))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-15.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Border(new Tincture[]{Tincture.OR, Tincture.VERT}, Border.DIV_PER_PALE))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-16.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new BorderInvected(new Tincture[]{Tincture.OR, Tincture.GULES}, Border.DIV_PER_PALE))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-17.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new BorderInvected(new Tincture[]{Tincture.OR, Tincture.AZURE}, Border.DIV_PER_PALE))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-18.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Star(Tincture.GULES, Shield.Position.CHIEF_MID_DEXTER, 1.0))
                .add(new Star(Tincture.GULES, Shield.Position.CHIEF_MID_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-19.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new FleurDeLis(Tincture.GULES, Shield.Position.CHIEF_MID_DEXTER, 1.0))
                .add(new FleurDeLis(Tincture.GULES, Shield.Position.CHIEF_MID_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-20.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Crescent(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Border(Tincture.OR))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-21.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE, Shield.VariationOfLine.INVECTED)
                .add(new Star(Tincture.GULES, Shield.Position.CHIEF_MID_SINISTER, 1.0))
                .add(new Star(Tincture.GULES, Shield.Position.CHIEF_MID_DEXTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-22.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });



        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Border(new Tincture[]{Tincture.OR, Tincture.GULES}, Border.DIV_PER_FESS))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-23.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        fileWriter = fileWriterFactory.getInstance();
        builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.ARGENT)
                .fess(Tincture.SABLE)
                .add(new Border(new Tincture[]{Tincture.OR, Tincture.GULES}, Border.DIV_QUARTERLY))
                .build(fw -> {
                    try {
                        fw.writeToFile("stodart-24.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

    }
}
