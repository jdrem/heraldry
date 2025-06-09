package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawEverything {
        public static void main(String[] args) {
            BufferedImage bufferedImage = new BufferedImage(200, 250, BufferedImage.TYPE_INT_ARGB);
            Builder builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .fess(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("fess.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .pale(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("pale.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .chief(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("chief.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });           builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .cross(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("cross.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .bend(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("bend.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .bendSinister(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("bend-sinister.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                .saltire(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("saltire.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            builder = new Builder(bufferedImage.createGraphics());
            builder.fieldOf(Tincture.OR)
                    .chevron(Tincture.SABLE)
                    .build(g -> {
                        try {
                            ImageIO.write(bufferedImage, "PNG", new File("chevron.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        }
}
