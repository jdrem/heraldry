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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class PNGFileWriter implements FileWriter {
    final private BufferedImage bufferedImage;
    public PNGFileWriter(int width, int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    @Override
    public Graphics2D createGraphics() {
        return bufferedImage.createGraphics();
    }

    final static Pattern pngExtPattern = Pattern.compile(".*\\.(?:png|PNG)");
    @Override
    public void writeToFile(String fileName) throws IOException {
        if (pngExtPattern.matcher(fileName).matches())
            ImageIO.write(bufferedImage, "PNG", new File(fileName));
        else
            ImageIO.write(bufferedImage, "PNG", new File(fileName+".png"));
    }
}
