package net.remgant.heraldry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class Test {
    private String imageFileName;

    private Test(String args[]) {
        imageFileName = args[0];
    }

    private void run() throws Exception {
        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();

        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        Graphics2D g = (Graphics2D) svgGenerator;
        Area image = new Area();
        Rectangle2D.Double r1 = new Rectangle2D.Double(50.0, 50.0, 100.0, 50.0);
        Area a1 = new Area(r1);
        g.setColor(Color.red);
        g.fill(a1);
        //Rectangle2D.Double r2 = new Rectangle2D.Double(100.0,75.0,100.0,50.0)
        //Area a2 = new Area(r2);
        Ellipse2D.Double e2 = new Ellipse2D.Double(100.0, 75.0, 50.0, 50.0);
        Area a2 = new Area(e2);
        g.setColor(Color.blue);
        g.fill(a2);
        image.add(a1);
        image.add(a2);

        FileOutputStream imageFileStream = new FileOutputStream(imageFileName);
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(imageFileStream, "UTF-8");
        svgGenerator.stream(out, useCSS);
    }

    public static void main(String args[]) {
        try {
            Test t = new Test(args);
            t.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
