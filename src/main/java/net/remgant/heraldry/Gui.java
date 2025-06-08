package net.remgant.heraldry;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;

public class Gui extends JFrame {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel
                    ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }
        Gui frame = new Gui();
        frame.pack();
        frame.setVisible(true);
    }

    int screenSizeX;
    int screenSizeY;
    GuiPanel panel;

    Gui() {
        super("Gui");
        screenSizeX = 200;
        screenSizeY = 200;
        setBounds(0, 0, screenSizeX, screenSizeY);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JMenuBar myMenuBar = new JMenuBar();
        setJMenuBar(myMenuBar);
        JMenu FileMenu = new JMenu("File");
        myMenuBar.add(FileMenu);

        JMenuItem ExitMenuItem = new JMenuItem("Exit");
        ExitMenuItem.addActionListener(e -> System.exit(1));
        FileMenu.add(ExitMenuItem);

        Dimension size = new Dimension(screenSizeX, screenSizeY);
        panel = new GuiPanel(size);
        getContentPane().add("Center", panel);
        panel.setPreferredSize(size);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int state = panel.getState();
                switch (state) {
                    case 0:
                        panel.identityTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 1:
                        panel.quadrantRotateTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 2:
                        panel.rotateTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 3:
                        panel.shearTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 4:
                        panel.translateTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 5:
                        panel.scaleTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                    case 6:
                        panel.flipTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100},
                                        -1.0, 1.0);
                        break;
                    case 7:
                        panel.flipTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100},
                                        1.0, -1.0);
                        break;
                    case 8:
                        panel.flipTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100},
                                        -1.0, -1.0);
                        break;
                    default:
                        panel.identityTransform
                                (Color.red, new int[]{50, 50}, new int[]{0, 0},
                                        new int[]{100, 0}, new int[]{100, 100});
                        break;
                }
                panel.incrState();
            }
        });
        panel.clear(Color.white);
      /*
      panel.drawLine(100,0,100,199,Color.red);
      panel.drawLine(0,100,199,100,Color.red);
      panel.drawRectangle(100,100,20,20,Color.black);
      panel.drawAndTranslateRectangle(100,100,20,20,-20,-20,Color.blue);
      // panel.drawAndRotateRectangle(100,100,20,20,Math.PI/4.0,Color.green);
      panel.drawAndRotateRectangle(100,100,20,20,-Math.PI/4.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,-3.0*Math.PI/16.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,-Math.PI/8.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,-Math.PI/16.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,Math.PI/16.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,Math.PI/8.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,3.0*Math.PI/16.0,
				      Color.cyan);
      panel.drawAndRotateRectangle(100,100,20,20,Math.PI/4.0,
				      Color.cyan);

      panel.drawAndRotateRectangle(50,50,20,20,-Math.PI/4.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,-3.0*Math.PI/16.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,-Math.PI/8.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,-Math.PI/16.0,
				      Color.magenta);
      panel.drawRectangle(50,50,20,20,Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,Math.PI/16.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,Math.PI/8.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,3.0*Math.PI/16.0,
				      Color.magenta);
      panel.drawAndRotateRectangle(50,50,20,20,Math.PI/4.0,
				      Color.magenta);

      panel.drawAndRotateRectangle(150,150,20,20,-Math.PI/8.0,
				      Color.yellow);
      panel.drawAndRotateRectangle(150,150,20,20,-Math.PI/16.0,
				      Color.yellow);
      panel.drawRectangle(150,150,20,20,Color.yellow);
      panel.drawAndRotateRectangle(150,150,20,20,Math.PI/16.0,
				      Color.yellow);
      panel.drawAndRotateRectangle(150,150,20,20,Math.PI/8.0,
				      Color.yellow);

      // panel.drawAndRotateRectangle(0,0,20,20,Math.PI/4.0,Color.black);
      panel.drawRotateAndTranslateRectangle(-10,-10,20,20,25,25,Math.PI/4.0,
					    Color.black);
      */
    }

    static class GuiPanel extends JPanel implements ActionListener {
        private final Dimension d;
        private final BufferedImage image;
        private final Timer timer;

        private int state;

        public int getState() {
            return state;
        }

        public void incrState() {
            state++;
        }

        public GuiPanel(Dimension d) {
            super();
            this.d = d;
            image = new BufferedImage(d.width, d.height,
                    BufferedImage.TYPE_INT_ARGB);
            timer = new Timer(250, this);
            timer.start();
            state = 0;
        }

        public void clear(Color c) {
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(c);
                g.fill(new Rectangle2D.Float(0.0f, 0.0f, (float) d.width,
                        (float) d.height));
            }
        }

        public void drawLine(int x1, int y1, int x2, int y2, Color c) {
            synchronized (image) {
                Graphics g = image.getGraphics();
                g.setColor(c);
                g.drawLine(x1, y1, x2, y2);
            }
        }

        public void drawRectangle(int x, int y, int w, int h, Color c) {
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                Rectangle2D.Float r = new Rectangle.Float((float) x, (float) y,
                        (float) w, (float) h);
                g.setColor(c);
                g.fill(r);
            }
        }

        public void drawAndRotateRectangle(int x, int y, int h, int w,
                                           double rot, Color c) {
            Area a = new Area
                    (new Rectangle2D.Float((float) x, (float) y, (float) h, (float) w));
            AffineTransform t = new AffineTransform();
            t.rotate(rot);
            a.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(c);
                g.fill(a);
            }
        }

        public void drawAndTranslateRectangle(int x, int y, int h, int w,
                                              int dx, int dy, Color c) {
            Area a = new Area
                    (new Rectangle2D.Float((float) x, (float) y, (float) h, (float) w));
            AffineTransform t = new AffineTransform();
            t.translate(dx, dy);
            a.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(c);
                g.fill(a);
            }
        }

        public void drawTranslateAndRotateRectangle(int x, int y, int h, int w,
                                                    int dx, int dy, double rot,
                                                    Color c) {
            Area a = new Area
                    (new Rectangle2D.Float((float) x, (float) y, (float) h, (float) w));
            AffineTransform t = new AffineTransform();
            t.translate(dx, dy);
            t.rotate(rot);
            a.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(c);
                g.fill(a);
            }
        }

        public void drawRotateAndTranslateRectangle(int x, int y, int h, int w,
                                                    int dx, int dy, double rot,
                                                    Color c) {
            Area a = new Area
                    (new Rectangle2D.Float((float) x, (float) y, (float) h, (float) w));
            AffineTransform ta = new AffineTransform();
            ta.rotate(rot);
            a.transform(ta);
            AffineTransform tb = new AffineTransform();
            tb.translate(dx, dy);
            a.transform(tb);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(c);
                g.fill(a);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            synchronized (image) {
                g.drawImage(image, 0, 0, Color.gray, this);
            }
        }

        public void actionPerformed(ActionEvent e) {
            this.repaint();
        }

        public void identityTransform(Color color, int[] corner, int[] a, int[] b, int[] c) {
            System.out.println("Identity Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.setToIdentity();
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void quadrantRotateTransform(Color color, int corner[], int a[], int b[], int c[]) {
            System.out.println("Quadrant Rotate Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.rotate(Math.PI);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(100.0, 100.0);
            area.transform(t);
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void rotateTransform(Color color, int corner[], int a[], int b[], int c[]) {
            System.out.println("Rotate Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.rotate(Math.PI / 6.0);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(100.0, 100.0);
            area.transform(t);
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void shearTransform(Color color, int corner[], int a[], int b[], int c[]) {
            System.out.println("Shear Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.shear(-0.2, 0.0);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void translateTransform(Color color, int corner[], int a[], int b[], int c[]) {
            System.out.println("Translate Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.translate(20.0, 20.0);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void scaleTransform(Color color, int corner[], int a[], int b[], int c[]) {
            System.out.println("Scale Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform();
            t.scale(0.75, 0.75);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

        public void flipTransform(Color color, int corner[], int a[], int b[], int c[], double m0, double m1) {
            System.out.println("Flip Transform");
            this.clear(Color.white);
            GeneralPath path = new GeneralPath();
            path.moveTo(a[0], a[1]);
            path.lineTo(b[0], b[1]);
            path.lineTo(c[0], c[1]);
            path.lineTo(a[0], a[1]);
            Area area = new Area(path);
            AffineTransform t = new AffineTransform(m0, 0.0, 0.0, m1, 0.0, 0.0);
            area.transform(t);
            System.out.println(t.getType() + " " + t.toString());
            t.setToIdentity();
            t.translate(corner[0], corner[1]);
            area.transform(t);
            synchronized (image) {
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fill(area);
            }
        }

    }
}
