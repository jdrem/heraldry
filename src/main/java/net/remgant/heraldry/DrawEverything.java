package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class DrawEverything {
    public static void main(String[] args) {
        FileWriterFactory fileWriterFactory = new FileWriterFactory("SVG", new Properties());

        Builder builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .fess(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("fess.svg");
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .pale(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("pale.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .chief(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("chief.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .cross(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("cross.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .bend(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("bend.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .bendSinister(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("bend-sinister.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .saltire(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("saltire.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        builder = new Builder(fileWriterFactory.getInstance());
        builder.fieldOf(Tincture.OR)
                .chevron(Tincture.SABLE)
                .build(fw -> {
                    try {
                        fw.writeToFile("chevron.svg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
