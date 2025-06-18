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

    https://commons.wikimedia.org/wiki/File:Martlet_Fourth_son.svg

    The file was created by Wikimedia user Hstoops (https://commons.wikimedia.org/wiki/User:Hstoops).

    The file is licensed under the Creative Commons Attribution 4.0 International license.
    (https://creativecommons.org/licenses/by/4.0/deed.en)
 */

package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Martlet extends Charge {
    protected Martlet(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
    }

    private final static Shape[] shapes;
    private final static Shape completeShape;
    static {
        shapes = new Shape[7];
        Path2D[] paths = new Path2D[7];

        paths[0] = new Path2D.Double();
        paths[0].moveTo(8.851560, 44.435902);
        paths[0].curveTo(14.874900, 42.144798, 17.313900, 39.853699, 21.193899, 37.562599);
        paths[0].lineTo(22.413401, 30.985001);
        paths[0].curveTo(34.792702, 31.095800, 42.959400, 27.696100, 45.324402, 23.409500);
        paths[0].curveTo(45.213600, 23.705200, 41.074799, 24.259501, 35.310101, 24.333401);
        paths[0].curveTo(31.577801, 24.407301, 27.771601, 24.629000, 25.295799, 24.037701);
        paths[0].curveTo(20.824400, 23.040001, 11.290500, 23.520399, 11.290500, 23.520399);
        paths[0].lineTo(13.175100, 35.234600);
        paths[0].curveTo(10.920900, 38.671200, 10.071000, 41.479698, 8.851560, 44.435902);

        paths[1] = new Path2D.Double();
        paths[1].moveTo(17.905100, 39.373501);
        paths[1].curveTo(17.905100, 39.373501, 12.066400, 36.675900, 9.479710, 33.978401);
        paths[1].curveTo(7.742910, 32.204601, 6.006110, 29.433100, 5.303990, 27.068100);
        paths[1].curveTo(4.786650, 25.109600, 4.934460, 22.892401, 5.710480, 21.007799);
        paths[1].curveTo(6.708220, 18.642799, 10.144900, 17.460300, 10.144900, 15.095200);
        paths[1].curveTo(10.144900, 15.095200, 10.477400, 14.393100, 10.144900, 13.580200);
        paths[1].curveTo(9.331900, 11.621600, 5.858290, 8.997960, 5.858290, 8.997960);
        paths[1].lineTo(10.588300, 8.184990);
        paths[1].curveTo(10.588300, 8.184990, 12.731600, 6.854670, 14.172800, 6.374280);
        paths[1].curveTo(15.687900, 5.893890, 17.498600, 5.856930, 19.013599, 6.374280);
        paths[1].curveTo(20.491800, 6.891630, 21.452600, 8.000220, 22.191601, 9.404450);
        paths[1].curveTo(23.078501, 11.141200, 22.893700, 13.247600, 22.709000, 15.206100);
        paths[1].curveTo(22.413300, 18.051500, 19.789700, 22.412001, 19.789700, 22.412001);
        paths[1].curveTo(19.789700, 22.412001, 24.889200, 23.964001, 27.919399, 25.183500);
        paths[1].curveTo(31.245199, 26.476801, 34.681801, 28.731001, 39.042301, 30.837299);
        paths[1].curveTo(44.733101, 33.571899, 50.165199, 34.717400, 50.165199, 34.717400);
        paths[1].curveTo(45.472198, 36.971600, 40.446499, 36.749802, 35.347000, 36.675900);
        paths[1].curveTo(41.000801, 40.408199, 47.467602, 40.038700, 54.008400, 39.853901);
        paths[1].curveTo(51.717300, 43.068901, 48.798000, 44.029598, 45.065701, 44.436100);
        paths[1].curveTo(47.024200, 47.614101, 48.243698, 50.348598, 48.650101, 54.783001);
        paths[1].curveTo(48.650101, 54.783001, 42.663700, 48.870499, 39.116199, 46.653301);
        paths[1].curveTo(35.642601, 44.473099, 27.771601, 41.812401, 27.771601, 41.812401);
        paths[1].curveTo(27.623800, 44.768700, 29.804001, 48.685699, 32.206001, 52.713600);
        paths[1].curveTo(28.067200, 51.605000, 24.556601, 49.535702, 22.265499, 45.101299);
        paths[1].curveTo(17.683300, 48.316200, 16.131300, 48.168400, 13.581500, 48.537899);
        paths[1].curveTo(16.242201, 46.062099, 17.794201, 42.958000, 17.905100, 39.373501);

        paths[2] = new Path2D.Double();
        paths[2].moveTo(15.614100, 22.929199);
        paths[2].curveTo(15.614100, 22.929199, 14.727200, 22.929199, 13.729500, 23.040100);
        paths[2].curveTo(12.694800, 23.150900, 11.549300, 23.409599, 10.958000, 24.185600);
        paths[2].curveTo(10.440700, 24.850800, 10.218900, 25.479000, 10.255900, 26.107201);
        paths[2].curveTo(10.255900, 26.735399, 10.514600, 27.326599, 10.847200, 27.954800);
        paths[2].curveTo(11.549300, 29.285200, 13.212200, 30.504601, 15.318500, 31.613199);
        paths[2].curveTo(17.461800, 32.721802, 20.085501, 33.756500, 22.783001, 34.569500);
        paths[2].curveTo(25.480600, 35.382401, 28.215200, 36.084599, 30.654100, 36.528000);
        paths[2].curveTo(31.873501, 36.749699, 32.834301, 36.823601, 33.832100, 36.897499);
        paths[2].curveTo(34.755901, 36.971401, 35.753601, 37.045300, 36.381802, 37.008400);
        paths[2].lineTo(37.500000, 36.500000);
        paths[2].curveTo(36.908699, 36.573898, 36.460800, 36.573898, 35.500000, 36.500000);
        paths[2].curveTo(34.576199, 36.389099, 31.984400, 36.084599, 30.838900, 35.862801);
        paths[2].curveTo(28.473801, 35.456299, 25.739300, 34.754200, 23.041700, 33.941299);
        paths[2].curveTo(20.344101, 33.128300, 17.794399, 32.130600, 15.725000, 31.021999);
        paths[2].curveTo(13.655600, 29.913401, 12.140500, 28.767799, 11.549300, 27.659201);
        paths[2].curveTo(11.253600, 27.068001, 11.031900, 26.550600, 11.031900, 26.107201);
        paths[2].curveTo(11.031900, 25.663700, 11.142800, 25.183300, 11.660100, 24.592100);
        paths[2].curveTo(12.029700, 24.111700, 12.805700, 23.779100, 13.692600, 23.483500);
        paths[2].lineTo(15.614100, 22.929199);

        paths[3] = new Path2D.Double();
        paths[3].moveTo(19.457701, 9.663090);
        paths[3].curveTo(19.457701, 11.473800, 20.122801, 14.282200, 18.090401, 16.795099);
        paths[3].curveTo(17.388300, 17.497200, 16.501400, 18.014500, 15.651500, 17.718901);
        paths[3].curveTo(15.651500, 17.718901, 14.727700, 17.497200, 14.284200, 17.312401);
        paths[3].curveTo(13.508200, 16.942900, 12.214800, 15.797300, 12.214800, 15.797300);
        paths[3].curveTo(13.249500, 17.386299, 14.395100, 18.199301, 15.577600, 18.421000);
        paths[3].curveTo(16.723101, 18.568800, 17.831699, 18.125401, 18.607800, 17.275499);
        paths[3].curveTo(20.159800, 15.538700, 20.861900, 12.397600, 19.457701, 9.663090);

        paths[4] = new Path2D.Double();
        paths[4].moveTo(17.905001, 39.373501);
        paths[4].curveTo(17.905001, 39.373501, 12.066400, 36.675900, 9.479710, 33.978401);
        paths[4].curveTo(7.742910, 32.204601, 6.006110, 29.433100, 5.303990, 27.068100);
        paths[4].curveTo(4.786650, 25.109600, 4.934460, 22.892401, 5.710480, 21.007799);
        paths[4].curveTo(6.708220, 18.642799, 10.292700, 17.460300, 10.292700, 15.095200);
        paths[4].curveTo(10.292700, 15.095200, 10.514400, 14.393100, 10.144900, 13.580200);
        paths[4].curveTo(9.331900, 11.621600, 5.858290, 8.997960, 5.858290, 8.997960);
        paths[4].lineTo(10.551400, 8.184990);
        paths[4].curveTo(10.551400, 8.184990, 12.694600, 6.854670, 14.135800, 6.374280);
        paths[4].curveTo(15.650900, 5.893890, 17.461599, 5.856930, 18.976700, 6.374280);
        paths[4].curveTo(20.454800, 6.891630, 21.415600, 8.000220, 22.154699, 9.404450);
        paths[4].curveTo(23.041599, 11.141200, 22.856800, 13.247600, 22.672001, 15.206100);
        paths[4].curveTo(22.376400, 18.051500, 19.752701, 22.412001, 19.752701, 22.412001);
        paths[4].curveTo(19.752701, 22.412001, 24.852301, 23.964001, 27.882401, 25.183500);
        paths[4].curveTo(31.208200, 26.476801, 34.644901, 28.731001, 39.005299, 30.837299);
        paths[4].curveTo(44.696098, 33.571899, 50.128300, 34.717400, 50.128300, 34.717400);
        paths[4].curveTo(45.435200, 36.971600, 40.409599, 36.749802, 35.310001, 36.675900);
        paths[4].curveTo(40.963902, 40.408199, 47.430698, 40.038700, 53.971401, 39.853901);
        paths[4].curveTo(51.680302, 43.068901, 48.761002, 44.029598, 45.028702, 44.436100);
        paths[4].curveTo(46.987202, 47.614101, 48.206699, 50.348598, 48.613201, 54.783001);
        paths[4].curveTo(48.613201, 54.783001, 42.626801, 48.870499, 39.079300, 46.653301);
        paths[4].curveTo(35.605598, 44.473099, 27.734600, 41.812401, 27.734600, 41.812401);
        paths[4].curveTo(27.586800, 44.768700, 29.767000, 48.685699, 32.168999, 52.713600);
        paths[4].curveTo(28.030199, 51.605000, 24.519699, 49.535702, 22.228600, 45.101299);
        paths[4].curveTo(17.646400, 48.316200, 16.094299, 48.168400, 13.544600, 48.537899);
        paths[4].curveTo(16.242201, 46.062099, 17.794201, 42.958000, 17.905001, 39.373501);

        paths[5] = new Path2D.Double();
        paths[5].moveTo(14.726600, 11.215300);
        paths[5].curveTo(15.236800, 11.215300, 15.650400, 10.801700, 15.650400, 10.291500);
        paths[5].curveTo(15.650400, 9.781290, 15.236800, 9.367680, 14.726600, 9.367680);
        paths[5].curveTo(14.216300, 9.367680, 13.802700, 9.781290, 13.802700, 10.291500);
        paths[5].curveTo(13.802700, 10.801700, 14.216300, 11.215300, 14.726600, 11.215300);

        paths[6] = new Path2D.Double();
        paths[6].moveTo(14.578200, 9.700200);
        paths[6].curveTo(14.319500, 9.700200, 14.134800, 9.921920, 14.134800, 10.143600);
        paths[6].curveTo(14.134800, 10.365400, 14.356500, 10.587100, 14.578200, 10.587100);
        paths[6].curveTo(14.799900, 10.587100, 14.984700, 10.439300, 14.984700, 10.143600);
        paths[6].curveTo(14.984700, 9.884960, 14.836900, 9.700200, 14.578200, 9.700200);

        Area completeArea = new Area();
        for (Path2D path : paths) {
            completeArea.add(new Area(path));
        }
        completeShape = completeArea;
        Rectangle2D bounds = completeArea.getBounds2D();
        System.out.printf("Bounds: %s%n", bounds);
        for (int i=0; i<paths.length; i++) {
            paths[i].transform(AffineTransform.getTranslateInstance(-bounds.getX(), -bounds.getY()));
            paths[i].transform(AffineTransform.getTranslateInstance(-bounds.getWidth()/2.0, -bounds.getHeight()/2.0));
            shapes[i] = paths[i];
        }
    }

    @Override
    public void draw(Graphics2D graphics, AffineTransform affineTransform) {
        Area shield = new Area(Shield.shieldShape);
        for (int i=0; i<shapes.length; i++) {
            Area a = new Area(shapes[i]);
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
            switch(i) {
                case 0:
                    tincture.fill(graphics, a);
                    secondary.draw(graphics, a);
                    break;
                case 1:
                    tincture.fill(graphics, a);
                    break;
                case 2:
                    secondary.fill(graphics, a);
                    break;
                case 3:
                    secondary.fill(graphics, a);
                    break;
                case 4:
                    secondary.draw(graphics, a);
                    break;
                case 5:
                    secondary.fill(graphics, a);
                    break;
                case 6:
                    secondary.fill(graphics, a);
                    break;
            }
        }
    }

    @Override
    public Shape getShape() {
        return completeShape;
    }
}
