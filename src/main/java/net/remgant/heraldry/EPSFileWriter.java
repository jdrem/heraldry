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

import org.apache.xmlgraphics.java2d.ps.EPSDocumentGraphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class EPSFileWriter implements FileWriter {
    final private EPSDocumentGraphics2D graphics;
    final private ByteArrayOutputStream byteArrayOutputStream;
    public EPSFileWriter(int width, int height) {
        graphics = new EPSDocumentGraphics2D(false);
        graphics.setGraphicContext(new org.apache.xmlgraphics.java2d.GraphicContext());
        byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            graphics.setupDocument(byteArrayOutputStream, width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Graphics2D createGraphics() {
        return graphics;
    }

    final static Pattern epsExtPattern = Pattern.compile(".*\\.(?:eps|EPS)");
    @Override
    public void writeToFile(String fileName) throws IOException {
        OutputStream fileOutputStream;
        if (epsExtPattern.matcher(fileName).matches())
            fileOutputStream = new FileOutputStream(fileName);
        else
            fileOutputStream = new FileOutputStream(fileName+".eps");
        graphics.finish();
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }
}
