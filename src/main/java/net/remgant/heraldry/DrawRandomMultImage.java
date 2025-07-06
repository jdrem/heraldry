package net.remgant.heraldry;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class DrawRandomMultImage extends DrawRandom {
    public static void main(String[] args) {
        DrawRandom drawRandom = new DrawRandomMultImage();
        drawRandom.run(args);
    }


    public void run(String[] args) {
        String imageFileType = "PNG";
        final String destinationDir;
        if (args.length >= 1 && (args[0].equalsIgnoreCase("PNG") || args[0].equalsIgnoreCase("SVG") ||
                args[0].equalsIgnoreCase("EPS"))) {
            imageFileType = args[0].toUpperCase();
            if (args.length >= 2)
                destinationDir = args[1] + FileSystems.getDefault().getSeparator() + ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
            else
                destinationDir = "." + FileSystems.getDefault().getSeparator() + ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
            if (!(new File(destinationDir)).exists()) {
                if (!(new File(destinationDir).mkdirs()))
                    throw new RuntimeException("can't create destination dir");
            }
        } else if (args.length == 1) {
            destinationDir = args[0];
        } else {
            destinationDir = ".";
        }
        AtomicInteger idx = new AtomicInteger(1);
        FileWriterFactory fileWriterFactory = new FileWriterFactory(imageFileType, new Properties());
        for (int i = 0; i < 500; i++) {
            FileWriter fileWriter = fileWriterFactory.getInstance();
            Builder builder = new Builder(fileWriter);
            RandomGenerator random = RandomGenerator.getDefault();
            drawShield(builder, random);
            builder.build(fw -> {
                try {
                    fw.writeToFile(String.format("%s/shield-%03d", destinationDir, idx.getAndIncrement()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.printf("shield-%03d: %s%n", idx.get() - 1, builder.getDescription());
        }
    }
}
