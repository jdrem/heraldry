/*
  Copyright 2026 Jeffrey D. Remillard <jdr@remgant.net>

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

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileTypesDemo {
    public static void main(String[] args) {
        new FileTypesDemo().run();
    }

    static class FileTypeInfo {
        FileWriter fileWriter;
        String fileName;

        public FileTypeInfo(FileWriter fileWriter, String fileName) {
            this.fileWriter = fileWriter;
            this.fileName = fileName;
        }

        public FileWriter getFileWriter() {
            return fileWriter;
        }

        public String getFileName() {
            return fileName;
        }
    }

    private void run() {
        int width = 200;
        int height = 250;
        List.of(new FileTypeInfo(new PNGFileWriter(width, height ),"output-format-demo.png"),
                new FileTypeInfo(new SVGFileWriter(width, height ),"output-format-demo.svg"),
                new FileTypeInfo(new EPSFileWriter(width, height ),"output-format-demo.eps"),
                new FileTypeInfo(new WebPFileWriter(width, height ),"output-format-demo.webp"))
                .forEach(fti -> {
                    Builder builder = new Builder(fti.getFileWriter());
                    builder.fieldOf(Tincture.AZURE)
                            .add(new Bend(Tincture.OR))
                            .add(new Crescent(Tincture.OR, Shield.Position.BEND_TOP_SINISTER, 1.5))
                            .add(new Crescent(Tincture.OR, Shield.Position.BEND_BOTTOM_DEXTER, 1.5))
                            .build(fw -> {
                                try {
                                    File f = new File(fti.getFileName());
                                    fw.writeToFile(f.getAbsolutePath());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
        });
    }
}
