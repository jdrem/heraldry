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

import java.util.Properties;

public class FileWriterFactory {
    final private String fileWriterType;
    final private Properties properties;
    public FileWriterFactory(String fileWriterType, Properties properties) {
        this.fileWriterType = fileWriterType;
        this.properties = properties;
        if (!(fileWriterType.equalsIgnoreCase("SVG") || fileWriterType.equalsIgnoreCase("PNG") ||
                fileWriterType.equalsIgnoreCase("EPS")))
            throw new RuntimeException("Unkown type: "+fileWriterType);
    }

    public FileWriter getInstance() {
        if (fileWriterType.equalsIgnoreCase("SVG"))
            return new SVGFileWriter();
        else if (fileWriterType.equalsIgnoreCase("PNG"))
            return new PNGFileWriter(Integer.parseInt(properties.getProperty("width", "200")),
                    Integer.parseInt(properties.getProperty("height","250")));
        else if (fileWriterType.equalsIgnoreCase("EPS"))
            return new EPSFileWriter(Integer.parseInt(properties.getProperty("width", "200")),
                    Integer.parseInt(properties.getProperty("height","250")));
        throw new RuntimeException("Unkown type: "+fileWriterType);
    }
}
