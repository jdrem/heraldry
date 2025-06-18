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
/*
    Parts of this class were derived from the SVG file:

    https://commons.wikimedia.org/wiki/File:Fleur-de-lys_Sixth_son.svg

    The file was created by Wikimedia user Hstoops (https://commons.wikimedia.org/wiki/User:Hstoops).

    The file is licensed under the Creative Commons Attribution 4.0 International license.
    (https://creativecommons.org/licenses/by/4.0/deed.en)
 */
package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class FleurDeLis extends Charge {
    protected FleurDeLis(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    private final static Shape[] shapes;
    private final static Shape completeShape;

    static {
        shapes = new Shape[4];
        Path2D[] paths = new Path2D[4];

        paths[0] = new Path2D.Double();
        paths[0].moveTo(29.627300, 6.021410);
        paths[0].curveTo(29.588900, 5.990690, 29.573601, 5.990690, 29.535200, 6.036770);
        paths[0].curveTo(27.822599, 7.518930, 26.302099, 8.893580, 25.111700, 10.421800);
        paths[0].curveTo(21.771099, 14.707000, 20.649900, 19.360901, 22.523701, 23.123899);
        paths[0].curveTo(26.233000, 31.095301, 27.646000, 33.115002, 27.646000, 39.458401);
        paths[0].lineTo(26.762899, 43.397999);
        paths[0].curveTo(26.639999, 45.425499, 25.226900, 47.038200, 22.569799, 47.944401);
        paths[0].curveTo(22.907700, 48.796799, 24.497400, 50.540100, 26.693701, 50.424900);
        paths[0].curveTo(27.123800, 52.268002, 27.553900, 53.297001, 29.535200, 54.971199);
        paths[0].curveTo(29.581301, 55.009602, 29.581301, 55.009602, 29.627300, 54.971199);
        paths[0].curveTo(31.608700, 53.297001, 32.038700, 52.268002, 32.468800, 50.424900);
        paths[0].curveTo(34.665199, 50.540100, 36.254799, 48.796799, 36.592701, 47.944401);
        paths[0].curveTo(33.935600, 47.038200, 32.522598, 45.425499, 32.399700, 43.397999);
        paths[0].lineTo(31.516500, 39.458401);
        paths[0].curveTo(31.516500, 33.115002, 32.929600, 31.095301, 36.638802, 23.123899);
        paths[0].curveTo(38.505001, 19.360901, 37.391399, 14.707000, 34.050800, 10.421800);
        paths[0].curveTo(32.860500, 8.885900, 31.347601, 7.503570, 29.627300, 6.021410);

        paths[1] = new Path2D.Double();
        paths[1].moveTo(26.495501, 44.119900);
        paths[1].curveTo(25.543301, 44.987701, 21.027700, 47.375999, 17.326099, 47.375999);
        paths[1].curveTo(22.156601, 43.797298, 23.899799, 42.722198, 24.468100, 40.011299);
        paths[1].curveTo(25.036400, 37.308102, 23.201000, 34.320702, 21.880100, 33.706299);
        paths[1].curveTo(21.066099, 33.184101, 19.307400, 32.953701, 17.756201, 33.552700);
        paths[1].curveTo(15.728700, 34.458900, 15.644300, 37.016201, 16.658001, 38.191200);
        paths[1].curveTo(7.066150, 36.808899, 8.018420, 29.628500, 11.282200, 27.025101);
        paths[1].curveTo(13.924000, 24.920900, 16.803900, 24.590700, 19.023300, 25.251101);
        paths[1].curveTo(24.068800, 26.656500, 28.108299, 31.325701, 28.269501, 38.298698);
        paths[1].curveTo(28.269501, 38.298698, 27.401699, 43.313499, 26.495501, 44.119900);
        paths[1].lineTo(26.495501, 44.119900);

        paths[2] = new Path2D.Double();
        paths[2].moveTo(32.561100, 44.119900);
        paths[2].curveTo(33.513401, 44.987701, 38.028999, 47.375999, 41.730499, 47.375999);
        paths[2].curveTo(36.900101, 43.797298, 35.156799, 42.722198, 34.588501, 40.011299);
        paths[2].curveTo(34.020199, 37.308102, 35.855598, 34.320702, 37.176498, 33.706299);
        paths[2].curveTo(37.990601, 33.184101, 39.749199, 32.953701, 41.300499, 33.552700);
        paths[2].curveTo(43.327900, 34.458900, 43.412399, 37.016201, 42.398701, 38.191200);
        paths[2].curveTo(51.990501, 36.808899, 51.038200, 29.628500, 47.774399, 27.025101);
        paths[2].curveTo(45.132599, 24.920900, 42.252800, 24.590700, 40.033401, 25.251101);
        paths[2].curveTo(34.987900, 26.656500, 30.948400, 31.325701, 30.787100, 38.298698);
        paths[2].curveTo(30.787100, 38.298698, 31.654900, 43.313499, 32.561100, 44.119900);
        paths[2].lineTo(32.561100, 44.119900);

        paths[3] = new Path2D.Double();
        paths[3].moveTo(37.414600, 36.647499);
        paths[3].lineTo(21.755899, 36.647499);
        paths[3].lineTo(21.755899, 40.789799);
        paths[3].lineTo(37.414600, 40.789799);
        paths[3].lineTo(37.414600, 36.647499);

        Area completeArea = new Area();
        for (Path2D path : paths) {
            completeArea.add(new Area(path));
        }
        completeShape = completeArea;
        Rectangle2D bounds = completeArea.getBounds2D();
        System.out.printf("Bounds: %s%n", bounds);
        for (int i = 0; i < paths.length; i++) {
            paths[i].transform(AffineTransform.getTranslateInstance(-bounds.getX(), -bounds.getY()));
            paths[i].transform(AffineTransform.getTranslateInstance(-bounds.getWidth() / 2.0, -bounds.getHeight() / 2.0));
            shapes[i] = paths[i];
        }
    }

    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Area shield = new Area(Shield.shieldShape);
        for (Shape shape : shapes) {
            Area a = new Area(shape);
            if (!affineTransform.isIdentity()) {
                a.transform(affineTransform);
            }
            if (scale != 1.0) {
                a.transform(AffineTransform.getScaleInstance(scale, scale));
            }
            double x = 0.0;
            double y = 0.0;
            if (position != null) {
                x = position.x() * shield.getBounds2D().getWidth() * affineTransform.getScaleX();
                y = position.y() * shield.getBounds2D().getHeight() * affineTransform.getScaleY();
            }

            a.transform(AffineTransform.getTranslateInstance(x, y));

            Tincture secondary;
            if (tincture.equals(Tincture.SABLE) || tincture.isFur())
                secondary = Tincture.ARGENT.darker().darker();
            else
                secondary = tincture.darker();

            tincture.fill(graphics, a);
            secondary.draw(graphics, a);
        }
    }

    @Override
    public Shape getShape() {
        return completeShape;
    }

    static void printIt() throws IOException {
        for (int i=0; i<shapes.length; i++) {
            BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Area a = new Area(shapes[i]);
            a.transform(AffineTransform.getTranslateInstance(50.0, 50.0));
            Graphics2D g = bufferedImage.createGraphics();
            g.setColor(Color.BLACK);
            g.fill(a);
            ImageIO.write(bufferedImage, "PNG", new File(String.format("fleur-de-lis-layer-%02d.png", i)));
        }
    }

    public static void main(String[] args) throws IOException {
        printIt();
    }
}
