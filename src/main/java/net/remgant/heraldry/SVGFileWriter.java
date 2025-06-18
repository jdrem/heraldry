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

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class SVGFileWriter implements FileWriter {

    final private SVGGraphics2D svgGenerator;
    public SVGFileWriter() {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        // Create an instance of the SVG Generator.
        svgGenerator = new SVGGraphics2D(document);
    }
    @Override
    public Graphics2D createGraphics() {
        return svgGenerator;
    }
    final static Pattern svgExtPattern = Pattern.compile(".*\\.(?:svg|SVG)");
    @Override
    public void writeToFile(String fileName) throws IOException {
        Writer out;
        if (svgExtPattern.matcher(fileName).matches())
            out = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);
        else
            out =  new OutputStreamWriter(new FileOutputStream(fileName+".svg"), StandardCharsets.UTF_8);
        svgGenerator.stream(out, true);
    }
}
