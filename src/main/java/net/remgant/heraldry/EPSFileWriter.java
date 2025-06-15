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
