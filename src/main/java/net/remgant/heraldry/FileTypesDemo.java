package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileTypesDemo {
    public static void main(String[] args) {
        new FileTypesDemo().run();
    }

    static class FileTypeInfo {
        FileWriter fileWriter;
        String fileName;

        public FileTypeInfo(FileWriter fileWriter, String fileName) {
            this.fileWriter = fileWriter;
            this.fileName = fileName;
        }

        public FileWriter getFileWriter() {
            return fileWriter;
        }

        public String getFileName() {
            return fileName;
        }
    }

    private void run() {
        int width = 200;
        int height = 250;
        List.of(new FileTypeInfo(new PNGFileWriter(width, height ),"output-format-demo.png"),
                new FileTypeInfo(new SVGFileWriter(width, height ),"output-format-demo.svg"),
                new FileTypeInfo(new EPSFileWriter(width, height ),"output-format-demo.eps"),
                new FileTypeInfo(new WebPFileWriter(width, height ),"output-format-demo.webp"))
                .forEach(fti -> {
                    Builder builder = new Builder(fti.getFileWriter());
                    builder.fieldOf(Tincture.AZURE)
                            .add(new Bend(Tincture.OR))
                            .add(new Crescent(Tincture.OR, Shield.Position.BEND_TOP_SINISTER, 1.5))
                            .add(new Crescent(Tincture.OR, Shield.Position.BEND_BOTTOM_DEXTER, 1.5))
                            .build(fw -> {
                                try {
                                    File f = new File(fti.getFileName());
                                    fw.writeToFile(f.getAbsolutePath());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
        });
    }
}
