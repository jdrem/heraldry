package net.remgant.heraldry;

import java.util.Properties;

public class FileWriterFactory {
    final private String fileWriterType;
    final private Properties properties;
    public FileWriterFactory(String fileWriterType, Properties properties) {
        this.fileWriterType = fileWriterType;
        this.properties = properties;
        if (!(fileWriterType.equalsIgnoreCase("SVG") || fileWriterType.equalsIgnoreCase("PNG")))
            throw new RuntimeException("Unkown type: "+fileWriterType);
    }

    public FileWriter getInstance() {
        if (fileWriterType.equalsIgnoreCase("SVG"))
            return new SVGFileWriter();
        else if (fileWriterType.equalsIgnoreCase("PNG"))
            return new PNGFileWriter(Integer.parseInt(properties.getProperty("width", "200")),
                    Integer.parseInt(properties.getProperty("height","250")));
        throw new RuntimeException("Unkown type: "+fileWriterType);
    }
}
