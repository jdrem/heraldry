package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
    Parts of this class were derived from the SVG file:

    https://commons.wikimedia.org/wiki/File:Rose_Seventh_son.svg

    The file was created by Wikimedia user Hstoops (https://commons.wikimedia.org/wiki/User:Hstoops).

    The file is licensed under the Creative Commons Attribution 4.0 International license.
    (https://creativecommons.org/licenses/by/4.0/deed.en)
 */

public class Rose extends Charge {
    private final Tincture barbs;
    private final Tincture seeds;

    protected Rose(Tincture tincture, Tincture seeds, Tincture barbs, Shield.Position position, double scale) {
        super(tincture, position, scale);
        this.barbs = barbs;
        this.seeds = seeds;
    }
    protected Rose(Tincture tincture, Shield.Position position, double scale) {
        super(tincture, position, scale);
        this.barbs = Tincture.VERT;
        this.seeds = Tincture.OR;
    }

    private final static Shape[] shapes;
    private final static Shape completeShape;

    static {
        shapes = new Shape[16];
        Path2D[] paths = new Path2D[16];

        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[0] = new Path2D.Double();
        paths[0].moveTo(28.121799, 36.437000);
        paths[0].lineTo(26.210899, 46.264400);
        paths[0].curveTo(28.394800, 50.086201, 29.486700, 52.542999, 29.759701, 54.999901);
        paths[0].curveTo(30.032700, 52.542999, 31.124599, 50.086201, 33.308498, 46.264400);
        paths[0].lineTo(31.397600, 36.437000);
        paths[0].lineTo(28.121799, 36.437000);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[1] = new Path2D.Double();
        paths[1].moveTo(22.244801, 29.786600);
        paths[1].lineTo(12.307900, 31.006100);
        paths[1].curveTo(9.348010, 34.264000, 7.348830, 36.061699, 5.096580, 37.080601);
        paths[1].curveTo(7.517550, 36.581001, 10.191600, 36.860298, 14.501100, 37.756302);
        paths[1].lineTo(23.257099, 32.902100);
        paths[1].lineTo(22.244801, 29.786600);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[2] = new Path2D.Double();
        paths[2].moveTo(26.752899, 22.140800);
        paths[2].lineTo(22.522400, 13.067000);
        paths[2].curveTo(18.509300, 11.258800, 16.181801, 9.913000, 14.516800, 8.085820);
        paths[2].curveTo(15.740100, 10.233900, 16.300800, 12.863400, 16.780399, 17.238899);
        paths[2].lineTo(24.102699, 24.066200);
        paths[2].lineTo(26.752899, 22.140800);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[3] = new Path2D.Double();
        paths[3].moveTo(35.417000, 24.066299);
        paths[3].lineTo(42.739300, 17.238899);
        paths[3].curveTo(43.218899, 12.863400, 43.779598, 10.233900, 45.002800, 8.085840);
        paths[3].curveTo(43.337898, 9.913020, 41.010399, 11.258800, 36.997200, 13.067100);
        paths[3].lineTo(32.766800, 22.140800);
        paths[3].lineTo(35.417000, 24.066299);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[4] = new Path2D.Double();
        paths[4].moveTo(36.264301, 32.901901);
        paths[4].lineTo(45.020199, 37.756100);
        paths[4].curveTo(49.329800, 36.860100, 52.003799, 36.580799, 54.424801, 37.080399);
        paths[4].curveTo(52.172600, 36.061600, 50.173401, 34.263901, 47.213501, 31.005899);
        paths[4].lineTo(37.276600, 29.786400);
        paths[4].lineTo(36.264301, 32.901901);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[5] = new Path2D.Double();
        paths[5].moveTo(24.575100, 8.319820);
        paths[5].curveTo(22.800699, 8.319820, 21.162800, 9.411760, 20.480301, 11.595600);
        paths[5].curveTo(19.797899, 13.779500, 19.931601, 15.417400, 20.941700, 16.918800);
        paths[5].lineTo(29.761801, 29.066601);
        paths[5].lineTo(38.581902, 16.918800);
        paths[5].curveTo(39.591900, 15.417400, 39.725700, 13.779500, 39.043201, 11.595600);
        paths[5].curveTo(38.360699, 9.411760, 36.722801, 8.319820, 34.948399, 8.319820);
        paths[5].curveTo(34.402500, 8.319820, 32.764599, 8.592810, 32.218601, 8.865790);
        paths[5].curveTo(30.853701, 9.411760, 30.307699, 9.411760, 29.761801, 9.411760);
        paths[5].curveTo(29.215799, 9.411760, 28.669800, 9.411760, 27.304899, 8.865790);
        paths[5].curveTo(26.758900, 8.592810, 25.121000, 8.319820, 24.575100, 8.319820);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[6] = new Path2D.Double();
        paths[6].moveTo(29.762400, 16.236300);
        paths[6].curveTo(29.489500, 21.695999, 28.397499, 24.971800, 27.851601, 27.701599);
        paths[6].lineTo(31.673300, 27.701599);
        paths[6].curveTo(31.127399, 24.971800, 30.035400, 21.695999, 29.762400, 16.236300);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[7] = new Path2D.Double();
        paths[7].moveTo(47.888500, 17.722601);
        paths[7].curveTo(47.340099, 16.035000, 45.795502, 14.814700, 43.507599, 14.840500);
        paths[7].curveTo(41.219799, 14.866300, 39.703400, 15.499700, 38.587601, 16.924200);
        paths[7].lineTo(29.759899, 29.066500);
        paths[7].lineTo(44.038700, 33.701000);
        paths[7].curveTo(45.778702, 34.197701, 47.377800, 33.818802, 49.243900, 32.494900);
        paths[7].curveTo(51.110001, 31.170900, 51.642300, 29.275801, 51.094002, 27.588200);
        paths[7].curveTo(50.925301, 27.069000, 50.159500, 25.595600, 49.731201, 25.160700);
        paths[7].curveTo(48.790199, 24.031300, 48.621498, 23.512100, 48.452702, 22.992800);
        paths[7].curveTo(48.284000, 22.473600, 48.115299, 21.954300, 48.212799, 20.487499);
        paths[7].curveTo(48.303699, 19.883900, 48.057201, 18.241800, 47.888500, 17.722601);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[8] = new Path2D.Double();
        paths[8].moveTo(41.961601, 25.101500);
        paths[8].curveTo(36.684799, 26.528999, 33.231899, 26.502800, 30.466900, 26.827200);
        paths[8].lineTo(31.647900, 30.461901);
        paths[8].curveTo(34.075401, 29.099100, 36.853500, 27.048300, 41.961601, 25.101500);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[9] = new Path2D.Double();
        paths[9].moveTo(46.151199, 42.802299);
        paths[9].curveTo(47.586700, 41.759399, 48.269901, 39.913300, 47.538399, 37.745300);
        paths[9].curveTo(46.806900, 35.577400, 45.735901, 34.330898, 44.036301, 33.709999);
        paths[9].lineTo(29.760401, 29.066500);
        paths[9].lineTo(29.765100, 44.078602);
        paths[9].curveTo(29.830400, 45.887001, 30.684999, 47.290699, 32.520699, 48.656300);
        paths[9].curveTo(34.356499, 50.021999, 36.323399, 49.942600, 37.758900, 48.899700);
        paths[9].curveTo(38.200600, 48.578800, 39.365299, 47.395199, 39.646500, 46.853401);
        paths[9].curveTo(40.429798, 45.609402, 40.871498, 45.288502, 41.313202, 44.967602);
        paths[9].curveTo(41.754902, 44.646702, 42.196602, 44.325802, 43.621799, 43.965199);
        paths[9].curveTo(44.223900, 43.865101, 45.709499, 43.123299, 46.151199, 42.802299);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[10] = new Path2D.Double();
        paths[10].moveTo(37.301701, 39.446400);
        paths[10].curveTo(34.313499, 34.868999, 33.271400, 31.577000, 32.108501, 29.047600);
        paths[10].lineTo(29.016600, 31.294001);
        paths[10].curveTo(31.062901, 33.181599, 33.871799, 35.189899, 37.301701, 39.446400);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[11] = new Path2D.Double();
        paths[11].moveTo(21.760500, 48.899799);
        paths[11].curveTo(23.195999, 49.942799, 25.163000, 50.022099, 26.998699, 48.656502);
        paths[11].curveTo(28.834499, 47.290798, 29.688999, 45.887100, 29.754400, 44.078701);
        paths[11].lineTo(29.759001, 29.066700);
        paths[11].lineTo(15.483200, 33.710098);
        paths[11].curveTo(13.783500, 34.331100, 12.712600, 35.577499, 11.981000, 37.745499);
        paths[11].curveTo(11.249500, 39.913399, 11.932800, 41.759499, 13.368300, 42.802502);
        paths[11].curveTo(13.810000, 43.123402, 15.295500, 43.865299, 15.897700, 43.965302);
        paths[11].curveTo(17.322800, 44.325901, 17.764500, 44.646801, 18.206200, 44.967701);
        paths[11].curveTo(18.647900, 45.288700, 19.089600, 45.609600, 19.872999, 46.853500);
        paths[11].curveTo(20.154200, 47.395302, 21.318800, 48.578899, 21.760500, 48.899799);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[12] = new Path2D.Double();
        paths[12].moveTo(22.217699, 39.446602);
        paths[12].curveTo(25.647699, 35.190102, 28.456600, 33.181801, 30.502800, 31.294201);
        paths[12].lineTo(27.410900, 29.047800);
        paths[12].curveTo(26.248100, 31.577200, 25.205999, 34.869202, 22.217699, 39.446602);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[13] = new Path2D.Double();
        paths[13].moveTo(8.425570, 27.587999);
        paths[13].curveTo(7.877250, 29.275499, 8.409600, 31.170700, 10.275700, 32.494598);
        paths[13].curveTo(12.141800, 33.818501, 13.740900, 34.197399, 15.480900, 33.700802);
        paths[13].lineTo(29.759701, 29.066200);
        paths[13].lineTo(20.931999, 16.924000);
        paths[13].curveTo(19.816200, 15.499400, 18.299801, 14.866000, 16.011900, 14.840200);
        paths[13].curveTo(13.724100, 14.814500, 12.179400, 16.034800, 11.631100, 17.722300);
        paths[13].curveTo(11.462400, 18.241600, 11.215900, 19.883600, 11.306800, 20.487301);
        paths[13].curveTo(11.404300, 21.954100, 11.235500, 22.473301, 11.066800, 22.992599);
        paths[13].curveTo(10.898100, 23.511801, 10.729400, 24.031099, 9.788380, 25.160500);
        paths[13].curveTo(9.360040, 25.595301, 8.594280, 27.068701, 8.425570, 27.587999);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[14] = new Path2D.Double();
        paths[14].moveTo(17.557301, 25.101601);
        paths[14].curveTo(22.665400, 27.048300, 25.443399, 29.099100, 27.871000, 30.461901);
        paths[14].lineTo(29.051901, 26.827200);
        paths[14].curveTo(26.287001, 26.502899, 22.834101, 26.529100, 17.557301, 25.101601);
        // Fill: rgb(234, 236, 240)
        // Stroke: rgb(163, 169, 176)
        paths[15] = new Path2D.Double();
        paths[15].moveTo(29.760099, 35.891399);
        paths[15].curveTo(33.529301, 35.891399, 36.584702, 32.835899, 36.584702, 29.066799);
        paths[15].curveTo(36.584702, 25.297701, 33.529301, 22.242201, 29.760099, 22.242201);
        paths[15].curveTo(25.990999, 22.242201, 22.935499, 25.297701, 22.935499, 29.066799);
        paths[15].curveTo(22.935499, 32.835899, 25.990999, 35.891399, 29.760099, 35.891399);

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

            if (i < 5) {
                barbs.fill(graphics, a);
                barbs.darker().draw(graphics, a);
            } else if (i < 15) {
                tincture.fill(graphics, a);
                secondary.draw(graphics, a);
            } else {
                seeds.fill(graphics, a);
                seeds.darker().draw(graphics, a);
            }
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
            ImageIO.write(bufferedImage, "PNG", new File(String.format("rose-layer-%02d.png", i)));
        }
    }

    public static void main(String[] args) throws IOException {
        printIt();
    }
}
