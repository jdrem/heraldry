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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class DrawEverything {
    public static void main(String[] args) {
        FileWriterFactory fileWriterFactory = new FileWriterFactory("SVG", new Properties());

        Builder builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .fess(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("fess.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .pale(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("pale.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .chief(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("chief.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .cross(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("cross.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .bend(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("bend.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .bendSinister(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("bend-sinister.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .saltire(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("saltire.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .chevron(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("chevron.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Star(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullet.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Annulet(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("annulet.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Crescent(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("crescent.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new FleurDeLis(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("fleur-de-li.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Heart(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("heart.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Heart(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("heart.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Lozenge(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("lozenge.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Martlet(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("marlet.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Rose(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("rose.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .add(new Roundel(Tincture.GULES, Shield.Position.CENTER, 3.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("roundel.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.HONOR_POINT_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.NAVEL_POINT, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.HONOR_POINT_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-default.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });




        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-fess.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-chief.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-bend.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-bend-sinister.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_TOP, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_BOTTOM, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-pale.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_SINISTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-saltire.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_TOP, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_BOTTOM, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-cross.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .build(fw -> {
                    try {
                        fw.writeToFile("or.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.ARGENT)
                .build(fw -> {
                    try {
                        fw.writeToFile("argent.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.GULES)
                .build(fw -> {
                    try {
                        fw.writeToFile("gules.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("sable.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.AZURE)
                .build(fw -> {
                    try {
                        fw.writeToFile("azure.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.VERT)
                .build(fw -> {
                    try {
                        fw.writeToFile("vert.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.PURPURE)
                .build(fw -> {
                    try {
                        fw.writeToFile("purpure.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.ERMINE)
                .build(fw -> {
                    try {
                        fw.writeToFile("ermine.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.ERMINES)
                .build(fw -> {
                    try {
                        fw.writeToFile("ermines.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.ERMINOIS)
                .build(fw -> {
                    try {
                        fw.writeToFile("erminois.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.PEAN)
                .build(fw -> {
                    try {
                        fw.writeToFile("pean.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
