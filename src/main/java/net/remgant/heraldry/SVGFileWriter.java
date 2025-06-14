package net.remgant.heraldry;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class SVGFileWriter implements FileWriter {

    final private SVGGraphics2D svgGenerator;
    public SVGFileWriter() {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        // Create an instance of the SVG Generator.
        svgGenerator = new SVGGraphics2D(document);
    }
    @Override
    public Graphics2D createGraphics() {
        return svgGenerator;
    }
    final static Pattern svgExtPattern = Pattern.compile(".*\\.(?:svg|SVG)");
    @Override
    public void writeToFile(String fileName) throws IOException {
        Writer out;
        if (svgExtPattern.matcher(fileName).matches())
            out = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);
        else
            out =  new OutputStreamWriter(new FileOutputStream(fileName+".svg"), StandardCharsets.UTF_8);
        svgGenerator.stream(out, true);
    }
}
