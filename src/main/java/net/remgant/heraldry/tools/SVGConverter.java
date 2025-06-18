/*
  Copyright 2025 Jeffrey D. Remillard <jdr@remgant.net>

  This file is part of the Remgant Heraldry Library hosted at https://github.com/jdrem/heraldry.

  The Remgant Heraldry Library is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
  License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
  later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program. If not,
  see <https://www.gnu.org/licenses/>.
 */
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

    String svgFileName;
    public SVGConverter(String svgFileName) {
        this.svgFileName = svgFileName;
    }

    Point2D currentPoint;

    public Point2D getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point2D currentPoint) {
        this.currentPoint = currentPoint;
    }

    public static void main(String args[]) throws URISyntaxException, IOException {
        new SVGConverter(args[0]).run();
    }

    public void run() throws URISyntaxException, IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        URI uri = new URI(String.format("file://%s", svgFileName)); // the URI of your SVG document
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
                    idx.incrementAndGet();
                }

                @Override
                public void movetoRel(float v, float v1) throws ParseException {
                    if (getCurrentPoint() == null) {
                        setCurrentPoint(new Point2D.Double(v, v1));
                    } else {
                        setCurrentPoint(new Point2D.Double(getCurrentPoint().getX()+v, getCurrentPoint().getY()+v1));
                    }
                    System.out.printf("     paths[%d].moveTo(%f, %f);%n",idx.get(), getCurrentPoint().getX(), getCurrentPoint().getY());
                    list.getLast().moveTo(getCurrentPoint().getX(), getCurrentPoint().getY());
                }

                @Override
                public void movetoAbs(float v, float v1) throws ParseException {
                    list.getLast().moveTo(v, v1);
                    setCurrentPoint(new Point2D.Double(v,v1));
                    System.out.printf("     paths[%d].moveTo(%f, %f);%n",idx.get(), v, v1);
                }

                @Override
                public void closePath() throws ParseException {
                }

                @Override
                public void linetoRel(float v, float v1) throws ParseException {
                    double cx = getCurrentPoint().getX();
                    double cy = getCurrentPoint().getY();
                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), v+cx, v1+cy);
                    setCurrentPoint(new Point2D.Double(v+cx, v1+cy));
               }

                @Override
                public void linetoAbs(float v, float v1) throws ParseException {
                    list.getLast().lineTo(v, v1);
                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), v, v1);
                    setCurrentPoint(new Point2D.Double(v, v1));
                }

                @Override
                public void linetoHorizontalRel(float v) throws ParseException {
                    throw new UnsupportedOperationException(String.format("linetoHorizontalRel %f", v));
                }

                @Override
                public void linetoHorizontalAbs(float v) throws ParseException {
                    throw new UnsupportedOperationException(String.format("linetoHorizontalAbs %f", v));

//                    Point2D point = list.getLast().getCurrentPoint();
//                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), v, point.getY());
                }

                @Override
                public void linetoVerticalRel(float v) throws ParseException {
                    throw new UnsupportedOperationException(String.format("linetoVerticalRel %f", v));
                }

                @Override
                public void linetoVerticalAbs(float v) throws ParseException {
                    throw new UnsupportedOperationException(String.format("linetoVerticalAbs %f", v));
//                    Point2D point = list.getLast().getCurrentPoint();
//                    System.out.printf("     paths[%d].lineTo(%f, %f);%n", idx.get(), point.getX(), v);
                }

                @Override
                public void curvetoCubicRel(float x, float y, float x1, float y1, float x2, float y2) throws ParseException {
                    Point2D currentPoint = getCurrentPoint();
                    double cx = currentPoint.getX();
                    double cy = currentPoint.getY();
                    list.getLast().curveTo(x+cx, y+cy, x1+cx, y1+cy, x2+cx, y2+cx);
                    System.out.printf("     paths[%d].curveTo(%f, %f, %f, %f, %f, %f);%n", idx.get(), x+cx, y+cy, x1+cx, y1+cy, x2+cx, y2+cx);
                    setCurrentPoint(new Point2D.Double(x2+cx, y2+cx));
                }

                @Override
                public void curvetoCubicAbs(float x, float y, float x1, float y1, float x2, float y2) throws ParseException {
                    list.getLast().curveTo(x, y, x1, y1, x2, y2);
                    System.out.printf("     paths[%d].curveTo(%f, %f, %f, %f, %f, %f);%n", idx.get(), x, y, x1, y1, x2, y2);
                    setCurrentPoint(new Point2D.Double(x2, y2));
                }

                @Override
                public void curvetoCubicSmoothRel(float v, float v1, float v2, float v3) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoCubicSmoothRel %f %f %f %f", v, v1, v2, v3));
                }

                @Override
                public void curvetoCubicSmoothAbs(float v, float v1, float v2, float v3) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoCubicSmoothAbs %f %f %f %f", v, v1, v2, v3));
                }

                @Override
                public void curvetoQuadraticRel(float v, float v1, float v2, float v3) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoQuadraticRel %f %f %f %f", v, v1, v2, v3));
                }

                @Override
                public void curvetoQuadraticAbs(float v, float v1, float v2, float v3) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoQuadraticAbs %f %f %f %f", v, v1, v2, v3));
                }

                @Override
                public void curvetoQuadraticSmoothRel(float v, float v1) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoQuadraticSmoothRel %f %f", v, v1));
                }

                @Override
                public void curvetoQuadraticSmoothAbs(float v, float v1) throws ParseException {
                    throw new UnsupportedOperationException(String.format("curvetoQuadraticSmoothAbs %f %f", v, v1));
                }

                @Override
                public void arcRel(float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException {
                    throw new UnsupportedOperationException(String.format("arcRel %f %f %f %b %b %f %f", v, v1, v2, v, v1, v3, v4));
                }

                @Override
                public void arcAbs(float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException {
                    throw new UnsupportedOperationException(String.format("arcAbs %f %f %f %b %b %f %f", v, v1, v2, v, v1, v3, v4));
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
