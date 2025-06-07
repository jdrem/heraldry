package net.remgant.heraldry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;


public class Draw4
{
  public static void main(String args[])
    {
      try
	{
	  Draw4 d = new Draw4(args);
	  d.run();
	}
      catch (Exception e)
	{
	  e.printStackTrace();
	}
    }

  static HashMap colorMap;
  static 
    {
      colorMap = new HashMap();
      colorMap.put("or",Color.yellow);
      colorMap.put("argent",Color.white);
      colorMap.put("gules",Color.red);
      colorMap.put("azure",Color.blue);
      colorMap.put("sable",Color.black);
      colorMap.put("vert",Color.green);
      colorMap.put("purpure",Color.magenta);
    }
      
  private String imageFileName;
  private String args[];

  public Draw4(String args[])
    {
      imageFileName = args[0];
      this.args = args;
    }

  private void run() throws Exception
    {
      DOMImplementation domImpl =
          GenericDOMImplementation.getDOMImplementation();

      // Create an instance of org.w3c.dom.Document.
      String svgNS = "http://www.w3.org/2000/svg";
      Document document = domImpl.createDocument(svgNS, "svg", null);

      // Create an instance of the SVG Generator.
      SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
	  ArrayList al = new ArrayList();
      al.add(new Shield(Color.white));
      al.add(new FessEngrailed(Color.black));
      al.add(new Border(Color.red));
      for (Iterator i=al.iterator();i.hasNext(); )
      {
    	  Drawable d = (Drawable)i.next();
    	  d.draw((Graphics2D)svgGenerator);
      }

      FileOutputStream imageFileStream = new FileOutputStream(imageFileName);
      boolean useCSS = true; // we want to use CSS style attributes
      Writer out = new OutputStreamWriter(imageFileStream, "UTF-8");
      svgGenerator.stream(out, useCSS);
    }
}
