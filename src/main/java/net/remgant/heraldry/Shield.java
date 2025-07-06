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
package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Shield implements Drawable {
    public enum Position {
        CHIEF_DEXTER(0.1667, 0.15),
        CHIEF_MID_DEXTER(0.333, 0.15),
        CHIEF_CENTER(0.5, 0.15),
        CHIEF_MID_SINISTER(0.667, 0.15),
        CHIEF_SINISTER(0.8333, 0.15),
        FESS_DEXTER(0.1667, 0.5),
        FESS_DEXTER_MID(0.25, 0.5),
        FESS_SINISTER_MID(0.74, 0.5),
        CENTER(0.5, 0.5),
        FESS_SINISTER(0.8333, 0.5),
        BEND_TOP_DEXTER(0.225, 0.1958),
        BEND_TOP_SINISTER(0.775, 0.1958),
        BEND_BOTTOM_SINISTER(0.775, 0.8),
        BEND_BOTTOM_DEXTER(0.225, 0.8),
        BASE(0.5, 0.8),
        PALE_TOP(0.5, 0.1958),
        PALE_BOTTOM(0.5, 0.8),
        PALE_TOP_MID(0.5, 0.25),
        PALE_BOTTOM_MID(0.5, 0.75),
        HONOR_POINT(0.5, 0.3),
        HONOR_POINT_DEXTER(0.25, 0.3),
        HONOR_POINT_SINISTER(0.75, 0.3),
        NAVEL_POINT(0.5, 0.7);
        double x;
        double y;

        Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
        double x() {return x;}
        double y() {return y;}
    }

    public enum LineOfDivision {
        NONE,
        PER_PALE,
        PER_FESS,
        PER_BEND,
        PER_BEND_SINISTER,
        PER_CHEVRON,
        PER_CROSS,
        PER_SALTIRE;
    }

    public enum VariationOfLine {
        ENGRAILED,
        INVECTED,
        INDENTED
    }

    public static class ArrangementOfCharges {
        final private Position[] positions;

        public ArrangementOfCharges(Position[] positions) {
            this.positions = positions;
        }

        public Position[] getPositions() {
            return positions;
        }

        final public static ArrangementOfCharges IN_FESS =
                new ArrangementOfCharges(new Position[]{Position.FESS_DEXTER, Position.CENTER, Position.FESS_SINISTER});
        final public static ArrangementOfCharges IN_PALE =
                new ArrangementOfCharges(new Position[]{Position.PALE_TOP, Position.CENTER, Position.PALE_BOTTOM});
        final public static ArrangementOfCharges IN_CHIEF =
                new ArrangementOfCharges(new Position[]{Position.CHIEF_DEXTER, Position.CHIEF_CENTER, Position.CHIEF_SINISTER});
        final public static ArrangementOfCharges IN_BEND =
                new ArrangementOfCharges(new Position[]{Position.BEND_TOP_DEXTER,  Position.CENTER, Position.BEND_BOTTOM_SINISTER});
        final public static ArrangementOfCharges IN_BEND_SINISTER =
                new ArrangementOfCharges(new Position[]{ Position.BEND_TOP_SINISTER, Position.CENTER, Position.BEND_BOTTOM_DEXTER});
        final public static ArrangementOfCharges IN_SALTIRE =
                new ArrangementOfCharges(new Position[]{Position.BEND_TOP_SINISTER, Position.CENTER, Position.BEND_BOTTOM_DEXTER, Position.BEND_TOP_DEXTER, Position.BEND_BOTTOM_SINISTER});
        final public static ArrangementOfCharges IN_CROSS =
                new ArrangementOfCharges(new Position[]{Position.PALE_TOP, Position.CENTER, Position.PALE_BOTTOM, Position.FESS_DEXTER, Position.FESS_SINISTER});
        final public static ArrangementOfCharges DEFAULT =
                new ArrangementOfCharges(new Position[]{Position.HONOR_POINT_DEXTER,  Position.NAVEL_POINT, Position.HONOR_POINT_SINISTER   });
        final public static ArrangementOfCharges[] allArrangements = {IN_FESS, IN_PALE, IN_CHIEF, IN_BEND, IN_BEND_SINISTER, IN_SALTIRE, IN_CROSS, DEFAULT};
        final public static Map<String, ArrangementOfCharges> map;
        static {
            Map<String,ArrangementOfCharges> m  = new HashMap<>();
            m.put(Fess.class.getSimpleName(), IN_FESS);
            m.put(Pale.class.getSimpleName(), IN_PALE);
            m.put(Chief.class.getSimpleName(), IN_CHIEF);
            m.put(Bend.class.getSimpleName(), IN_BEND);
            m.put(BendSinister.class.getSimpleName(), IN_BEND_SINISTER);
            m.put(Saltire.class.getSimpleName(), IN_SALTIRE);
            m.put(Cross.class.getSimpleName(), IN_CROSS);
            map = Collections.unmodifiableMap(m);
        }
    }

    public static BufferedImage createImage() {
        // create an image
        BufferedImage image =
                new BufferedImage(200, 250, BufferedImage.TYPE_INT_ARGB);
        // get the graphics context
        Graphics2D g = image.createGraphics();

        // set the background to be transperant
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fill(new Rectangle2D.Float(0, 0, 200, 250));
        // unset transperancy
        g.setComposite(AlphaComposite.SrcOver);
        return image;
    }

    Tincture tincture;
    Tincture secondTincture;
    LineOfDivision lineOfDivision = LineOfDivision.NONE;
    public Shield(Tincture tincture) {
        this.tincture = tincture;
    }

    public Shield(Tincture tincture, Tincture secondTincture, LineOfDivision lineOfDivision) {
        this.tincture = tincture;
        this.secondTincture = secondTincture;
        this.lineOfDivision = lineOfDivision;
    }

    final public static Shape shieldShape;
    static {
        Area a = new Area();
        a.add(new Area(new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f)));
        a.add(new Area(new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f)));
        shieldShape = a;
    }

    @Override
    public String description() {
       if (secondTincture == null)
           return tincture.toString().toLowerCase();
       return String.format("%s %s and %s", lineOfDivision.name().toLowerCase().replace("_", " "), tincture.toString().toLowerCase(), secondTincture.toString().toLowerCase());
    }

    public void draw(Graphics2D g, AffineTransform affineTransform) {
        Rectangle2D.Float rect = new Rectangle2D.Float(0.0f, 0.0f, 200.0f, 155.0f);
        Ellipse2D.Float circ = new Ellipse2D.Float(0.0f, 50.0f, 200.0f, 200.0f);
        Area area = new Area();
        area.add(new Area(rect));
        area.add(new Area(circ));
//        area.transform(affineTransform);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fill(new Rectangle2D.Float(0, 0, (int)area.getBounds2D().getWidth(), (int)area.getBounds2D().getHeight()));
        // unset transperancy
        g.setComposite(AlphaComposite.SrcOver);
        if (lineOfDivision == LineOfDivision.PER_PALE) {
            Area mask = new Area(new Rectangle2D.Double(0.0, 0.0, area.getBounds2D().getWidth() / 2.0, area.getBounds2D().getHeight()));
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth() / 2.0, 0.0));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            if (!affineTransform.isIdentity()) {
                sinister.transform(affineTransform);
                dexter.transform(affineTransform);
            }
            tincture.fill(g, sinister);
            secondTincture.fill(g, dexter);
        } else if (lineOfDivision == LineOfDivision.PER_FESS) {
            Area mask = new Area(new Rectangle2D.Double(0.0, area.getBounds2D().getHeight() / 2.0, area.getBounds2D().getWidth(), area.getBounds2D().getHeight() / 2.0));
            Area top = new Area(area);
            top.subtract(mask);
            mask.transform(AffineTransform.getTranslateInstance(0.0, -area.getBounds2D().getHeight() / 2.0));
            Area bottom = new Area(area);
            bottom.subtract(mask);
            if (!affineTransform.isIdentity()) {
                top.transform(affineTransform);
                bottom.transform(affineTransform);
            }
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_BEND) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth(), 0.0);
            path.lineTo(0.0, 0.0);
            Area mask = new Area(path);
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getRotateInstance(Math.PI));
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            if (!affineTransform.isIdentity()) {
                sinister.transform(affineTransform);
                dexter.transform(affineTransform);
            }
            tincture.fill(g, dexter);
            secondTincture.fill(g, sinister);
        }else if (lineOfDivision == LineOfDivision.PER_BEND_SINISTER) {
            Path2D path = new Path2D.Double();
            path.moveTo(area.getBounds2D().getWidth(), 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth(), 0.0);
            Area mask = new Area(path);
            Area dexter = new Area(area);
            dexter.subtract(mask);
            mask.transform(AffineTransform.getRotateInstance(Math.PI));
            mask.transform(AffineTransform.getTranslateInstance(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()));
            Area sinister = new Area(area);
            sinister.subtract(mask);
            if (!affineTransform.isIdentity()) {
                sinister.transform(affineTransform);
                dexter.transform(affineTransform);
            }
            tincture.fill(g, dexter);
            secondTincture.fill(g, sinister);
        } else if (lineOfDivision == LineOfDivision.PER_CHEVRON) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(0.0, 2.0 * area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth() / 2.0, area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth(), 2.0 * area.getBounds2D().getHeight() / 3.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            path.lineTo(0.0, area.getBounds2D().getHeight());
            Area mask = new Area(path);
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            if (!affineTransform.isIdentity()) {
                top.transform(affineTransform);
                bottom.transform(affineTransform);
            }
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_SALTIRE) {
            Path2D path = new Path2D.Double();
            path.moveTo(area.getBounds2D().getWidth() / 2.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight() / 2.0);
            path.lineTo(0.0, area.getBounds2D().getHeight() / 2.0);
            path.lineTo(area.getBounds2D().getWidth() / 2.0, 0.0);
            Area mask = new Area();
            mask.add(new Area(path));
            path.transform(AffineTransform.getScaleInstance(1.0, -1.0));
            mask.add(new Area(path));
            mask.transform(AffineTransform.getTranslateInstance(0.0, area.getBounds2D().getHeight() / 2.0));
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            if (!affineTransform.isIdentity()) {
                top.transform(affineTransform);
                bottom.transform(affineTransform);
            }
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else if (lineOfDivision == LineOfDivision.PER_CROSS) {
            Path2D path = new Path2D.Double();
            path.moveTo(0.0,0.0);
            path.lineTo(area.getBounds2D().getWidth()/2.0, 0.0);
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(0.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(0.0, 0.0);
            path.moveTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight()/2.0);
            path.lineTo(area.getBounds2D().getWidth(), area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight());
            path.lineTo(area.getBounds2D().getWidth()/2.0, area.getBounds2D().getHeight()/2.0);
            Area mask = new Area();
            mask.add(new Area(path));
            Area top = new Area(area);
            top.subtract(mask);
            Area bottom = new Area(area);
            bottom.intersect(mask);
            if (!affineTransform.isIdentity()) {
                top.transform(affineTransform);
                bottom.transform(affineTransform);
            }
            tincture.fill(g, top);
            secondTincture.fill(g, bottom);
        } else {
            if (!affineTransform.isIdentity()) {
                area.transform(affineTransform);
            }
            tincture.fill(g, area);
        }
        if (tincture.equals(Tincture.ARGENT) || tincture.equals(Tincture.ERMINE) ||
                (secondTincture != null && secondTincture.equals(Tincture.ARGENT) ||
                        (secondTincture != null && secondTincture.equals(Tincture.ERMINE)))) {
            Tincture.SABLE.draw(g, area);
        }
    }

    @Override
    public Tincture getTincture() {
        return tincture;
    }
}
