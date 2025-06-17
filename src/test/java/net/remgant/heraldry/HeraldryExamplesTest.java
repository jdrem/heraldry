package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class HeraldryExamplesTest {
    @Test
    public void testStarsInFess() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.FESS_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-fess.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testMulletsInChief() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        BufferedImage bufferedImage = new BufferedImage(200, 250, BufferedImage.TYPE_INT_ARGB);
        Builder builder = new Builder(bufferedImage.createGraphics());
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CHIEF_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-chief.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testMulletsInBend() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-bend.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testMulletsInBendSinister() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_SINISTER, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-bend-sinister.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testMulletsInPale() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_TOP, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.PALE_BOTTOM, 1.0))
                .build(fw -> {
                    try {
                        fw.writeToFile("mullets-in-pale.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void testMulletsInSaltire() {
        FileWriter fileWriter = new PNGFileWriter(200, 250);
        Builder builder = new Builder(fileWriter);
        builder.fieldOf(Tincture.AZURE)
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_TOP_SINISTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.CENTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_DEXTER, 1.0))
                .add(new Star(Tincture.ARGENT, Shield.Position.BEND_BOTTOM_SINISTER, 1.0))
                .build(fw -> {
                    try {
                       fw.writeToFile("mullets-in-saltire.png");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
