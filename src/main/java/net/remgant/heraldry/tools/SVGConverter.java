package net.remgant.heraldry.tools;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMSVGElement;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PathHandler;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SVGConverter {

    public static void main(String args[]) throws URISyntaxException, IOException {
        new SVGConverter().run();
    }

    public void run() throws URISyntaxException, IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        URI uri = new URI("file:///home/jdr/Projects/Heraldry/Fleur-de-lys_Sixth_son.svg"); // the URI of your SVG document
        Document doc = f.createDocument(uri.toString());


        UserAgent      userAgent;
        DocumentLoader loader;
        BridgeContext  ctx;
        GVTBuilder     builder;
        GraphicsNode rootGN;

        userAgent = new UserAgentAdapter();
        loader    = new DocumentLoader(userAgent);
        ctx       = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        builder   = new GVTBuilder();
        rootGN    = builder.build(ctx, doc);

        SVGOMSVGElement myRootSVGElement = (SVGOMSVGElement) doc.getDocumentElement();

        //I want all the "path" elements for example
        NodeList nl = myRootSVGElement.getElementsByTagName("path");
        List<Path2D> list = new ArrayList<>();
        AtomicInteger idx = new AtomicInteger();

        for(int i=0;i<nl.getLength();++i){
            Element elt = (Element)nl.item(i);
            //I am interested in the "fill" value of the current path element
            String fillvalue = myRootSVGElement.getComputedStyle(elt, null).getPropertyValue("fill");
            System.out.printf("     // Fill: %s%n", fillvalue);
            String strokeValue = myRootSVGElement.getComputedStyle(elt, null).getPropertyValue("stroke");
            System.out.printf("     // Stroke: %s%n", strokeValue);
            //If I want to parse the "d" attribute
            String toParse = elt.getAttribute("d");
//            System.out.printf("d: %s%n", toParse);
            //This string can then be fed to a PathParser if you want to create the shapes yourself
            PathParser pathParser = new PathParser();
            Path2D path = null;
            pathParser.setPathHandler(new PathHandler() {
                @Override
                public void startPath() throws ParseException {
//                    System.out.println("start path");
                    list.add(new Path2D.Double());
                    System.out.printf("     paths[%d] = new Path2D.Double();%n", idx.get());
                }

                @Override
                public void endPath() throws ParseException {
//                    System.out.println("end path");
                    idx.incrementAndGet();
                }

                @Override
                public void movetoRel(float v, float v1) throws ParseException {
                    System.out.printf("Move to: %f %f%n", v, v1);
                }

                @Override
                public void movetoAbs(float v, float v1) throws ParseException {
//                    System.out.printf("Move abs: %f %f%n", v, v1);
                    list.getLast().moveTo(v, v1);
                    System.out.printf("     paths[%d].moveTo(%f, %f);%n",idx.get(), v, v1);
                }

                @Override
                public void closePath() throws ParseException {
//                    System.out.println("close path");
                }

                @Override
                public void linetoRel(float v, float v1) throws ParseException {
                    System.out.printf("line to rel: %f %f%n", v, v1);
                }

                @Override
                public void linetoAbs(float v, float v1) throws ParseException {
//                    System.out.printf("line to abs: %f %f%n", v, v1);
                    list.getLast().lineTo(v, v1);
                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), v, v1);
                }

                @Override
                public void linetoHorizontalRel(float v) throws ParseException {
                    System.out.printf("line to horiz rel: %f%n", v);
                }

                @Override
                public void linetoHorizontalAbs(float v) throws ParseException {
//                    System.out.printf("line to horiz abs: %f%n", v);
                    Point2D point = list.getLast().getCurrentPoint();
                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), v, point.getY());
                }

                @Override
                public void linetoVerticalRel(float v) throws ParseException {
                    System.out.printf("line to vertical rel: %f%n", v);
                }

                @Override
                public void linetoVerticalAbs(float v) throws ParseException {
//                    System.out.printf("line to vert abs: %f%n", v);
                    Point2D point = list.getLast().getCurrentPoint();
                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), point.getX(), v);
                }

                @Override
                public void curvetoCubicRel(float v, float v1, float v2, float v3, float v4, float v5) throws ParseException {
                    System.out.printf("curve cubic rel: %f %f %f %f %f %f%n",v, v1, v2, v3, v4, v5);
                }

                @Override
                public void curvetoCubicAbs(float v, float v1, float v2, float v3, float v4, float v5) throws ParseException {
//                    System.out.printf("curve cubic abs: %f %f %f %f %f %f%n",v, v1, v2, v3, v4, v5);
                    list.getLast().curveTo(v, v1, v2, v3, v4, v5);
                    System.out.printf("     paths[%d].curveTo(%f, %f, %f, %f, %f, %f);%n", idx.get(), v, v1, v2, v3, v4, v5);
                }

                @Override
                public void curvetoCubicSmoothRel(float v, float v1, float v2, float v3) throws ParseException {
                    System.out.printf("Curve Cubic Smooth rel: %f %f %f %f%n", v, v1, v2, v3);
                }

                @Override
                public void curvetoCubicSmoothAbs(float v, float v1, float v2, float v3) throws ParseException {
                    System.out.printf("Curve Cubic Smooth abs: %f %f %f %f%n", v, v1, v2, v3);

                }

                @Override
                public void curvetoQuadraticRel(float v, float v1, float v2, float v3) throws ParseException {
                    System.out.printf("Curve Quadratic rel: %f %f %f %f%n", v, v1, v2, v3);

                }

                @Override
                public void curvetoQuadraticAbs(float v, float v1, float v2, float v3) throws ParseException {
                    System.out.printf("Curve Quadratic abs: %f %f %f %f%n", v, v1, v2, v3);

                }

                @Override
                public void curvetoQuadraticSmoothRel(float v, float v1) throws ParseException {
                    System.out.printf("Curve Quadratic smooth rel: %f %f %f %f%n", v, v1);

                }

                @Override
                public void curvetoQuadraticSmoothAbs(float v, float v1) throws ParseException {
                    System.out.printf("Curve Quadratic smooth abs: %f %f %f %f%n", v, v1);

                }

                @Override
                public void arcRel(float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException {
                    System.out.printf("Arc rel: %f %f %b %b %f %f%n", v, v1, v2,b, b1, v3);

                }

                @Override
                public void arcAbs(float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException {
                    System.out.printf("Arc abs: %f %f %b %b %f %f%n", v, v1, v2,b, b1, v3);
                }
            });
            pathParser.parse(toParse);
        }
        list.forEach(c -> System.out.println(c.getBounds2D()));
        BufferedImage bufferedImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fill(new Rectangle2D.Float(0.0f, 0.0f, 60.0f, 60.0f));
        graphics.setColor(java.awt.Color.BLACK);
        list.forEach(p -> {
            graphics.setColor(java.awt.Color.WHITE);
            graphics.fill(p);
            graphics.setColor(java.awt.Color.BLACK);
            graphics.draw(p);
        });
        ImageIO.write(bufferedImage, "PNG", new File("out.png"));
    }
}
