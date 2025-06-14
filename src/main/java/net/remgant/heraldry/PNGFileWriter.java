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
