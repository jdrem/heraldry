package net.remgant.heraldry;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Chief implements Drawable {
    Color color;

    public Chief(Color c) {
        color = c;
    }

    public void draw(Graphics2D g) {
        Area chief = new Area();
        chief.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 75.0f)));
//        Area shield = new Area();
//        shield.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
//        shield.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        g.setColor(color);
        g.fill(chief);
    }

    public void draw(BufferedImage image) {
        draw(image.createGraphics());
    }}
