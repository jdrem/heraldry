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
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

@Ignore
public class FileWriterTest {

    @Test
    public void testPNGWriter()  {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter.createGraphics());
        builder.fieldOf(Tincture.AZURE)
                .add(new Rose(Tincture.GULES, Tincture.OR, Tincture.VERT, Shield.Position.CENTER, 4.0))
                .build(fw -> {
                    try {
                        File f = File.createTempFile("test","png");
                        fw.writeToFile(f.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testSVGWriter()  {
        FileWriter fileWriter = new SVGFileWriter();
        Builder builder = new Builder(fileWriter.createGraphics());
        builder.fieldOf(Tincture.AZURE)
                .add(new Rose(Tincture.GULES, Tincture.OR, Tincture.VERT, Shield.Position.CENTER, 4.0))
                .build(fw -> {
                    try {
                        File f = File.createTempFile("test","png");
                        fw.writeToFile(f.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testEPSWriter()  {
        FileWriter fileWriter = new EPSFileWriter(200, 250);
        Builder builder = new Builder(fileWriter.createGraphics());
        builder.fieldOf(Tincture.AZURE)
                .add(new Rose(Tincture.GULES, Tincture.OR, Tincture.VERT, Shield.Position.CENTER, 4.0))
                .build(fw -> {
                    try {
                        File f = File.createTempFile("test","png");
                        fw.writeToFile(f.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
