package net.remgant.heraldry;

import java.awt.*;
import java.io.IOException;

public interface FileWriter {
    Graphics2D createGraphics();
    void writeToFile(String fileName) throws IOException;
}
