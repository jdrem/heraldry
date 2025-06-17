package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
