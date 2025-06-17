package net.remgant.heraldry;

import net.remgant.heraldry.tinctures.Tincture;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.random.RandomGenerator;

public class DrawRandom {
    static Function<Tincture, Drawable> bend = Bend::new;
    static Function<Tincture, Drawable> bendSinister = BendSinister::new;
    static Function<Tincture, Drawable> chenvron = Chevron::new;
    static Function<Tincture, Drawable> chief = Chief::new;
    static Function<Tincture, Drawable> cross = Cross::new;
    static Function<Tincture, Drawable> fess = Fess::new;
    static Function<Tincture, Drawable> pale = Pale::new;
    static Function<Tincture, Drawable> saltire = Saltire::new;

    final static List<Function<Tincture, Drawable>> ordinaries = List.of(
            bend,
            bendSinister,
            chenvron,
            chief,
            cross,
            fess,
            pale,
            saltire
    );

    @FunctionalInterface
    public interface MultiFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> annulet = Annulet::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> crescent = Crescent::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> ermineSpot = ErmineSpot::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> fleurDeLis = FleurDeLis::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> heart = Heart::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> lozenge = Lozenge::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> martlet = Martlet::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> rose = Rose::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> roundel = Roundel::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> star = Star::new;

    final static List<MultiFunction<Tincture, Shield.Position, Double, Drawable>> charges = List.of(
            annulet,
            crescent,
            ermineSpot,
            fleurDeLis,
            heart,
            lozenge,
            martlet,
            rose,
            roundel,
            star
    );

    public static void main(String[] args) {
        String imageFileType = "PNG";
        final String destinationDir;
        if (args.length >= 1 && (args[0].equalsIgnoreCase("PNG") || args[0].equalsIgnoreCase("SVG") ||
                args[0].equalsIgnoreCase("EPS")) ) {
            imageFileType = args[0].toUpperCase();
            if (args.length >= 2)
                destinationDir = args[1];
            else
                destinationDir = ".";
        }
        else if (args.length == 1) {
            destinationDir = args[0];
        }
        else {
            destinationDir = ".";
        }
        AtomicInteger idx = new AtomicInteger(1);
        FileWriterFactory fileWriterFactory = new FileWriterFactory(imageFileType, new Properties());
        for (int i = 0; i < 500; i++) {
//            FileWriter fileWriter = new PNGFileWriter(200, 250);
            FileWriter fileWriter = fileWriterFactory.getInstance();
            Builder builder = new Builder(fileWriter);
            RandomGenerator random = RandomGenerator.getDefault();
            double d = random.nextDouble();
            Tincture fieldTincture = null;
            Tincture chargeTincture = null;
            Tincture ordinaryTincture = null;
            Tincture t1 = null;
            Tincture t2 = null;
            Shield.LineOfDivision lineOfDivision = null;
            if (d < 0.15) {
                // split the field
                t1 = pickAnyTincture(random);
                t2 = t1;
                while (t2.equals(t1))
                    t2 = pickAnyTincture(random);
                int ii = random.nextInt(Shield.LineOfDivision.values().length);
                lineOfDivision = Shield.LineOfDivision.values()[ii];
                builder.add(new Shield(t1, t2, lineOfDivision));
            } else {
                fieldTincture = pickAnyTincture(random);
                builder.add(new Shield(fieldTincture));
            }
//            Tincture ordinaryColor;
//            if (fieldTincture.isMetal())
//                ordinaryColor = pickNonMetal(random);
//            else
//                ordinaryColor = pickAnyTincture(random);
            // now decide what to do
            d = random.nextDouble();
            if (d < 0.60 && lineOfDivision != null &&
                    !(t1.isFur() && t2.isFur()) &&
                    !(t1.isMetal() && t2.isMetal()) &&
                    !(t1.isColor() && t2.isColor())){
                // Do a counter charge
                MultiFunction<Tincture, Shield.Position, Double, Drawable> chargeFunc = charges.get(random.nextInt(charges.size()));
                switch (lineOfDivision) {
                    case PER_PALE:
                    case PER_BEND:
                    case PER_BEND_SINISTER:
                        builder.add(chargeFunc, t2, Shield.Position.FESS_DEXTER_MID, 2.0);
                        builder.add(chargeFunc, t1, Shield.Position.FESS_SINISTER_MID, 2.0);
                        break;
                    case PER_FESS:
                        builder.add(chargeFunc, t2, Shield.Position.PALE_TOP_MID, 2.0);
                        builder.add(chargeFunc, t1, Shield.Position.PALE_BOTTOM_MID, 2.0);
                        break;
                }
            }
            else if (d < 0.40) {
                // do an ordinary
                Function<Tincture, Drawable> func = ordinaries.get(random.nextInt(ordinaries.size()));
                if (fieldTincture == null) {
                    ordinaryTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                } else if (fieldTincture.isMetal())
                    ordinaryTincture = pickNonMetal(random);
                else
                    ordinaryTincture = pickNonColor(random);
                builder.add(func, ordinaryTincture);
                // should we add charges to the ordinary?
                d = random.nextDouble();
                if (d < 0.35 && fieldTincture != null) {
                    // Add charges to ordinary
                    MultiFunction<Tincture, Shield.Position, Double, Drawable> chargeFunc = charges.get(random.nextInt(charges.size()));
                    String simpleName = func.apply(ordinaryTincture).getClass().getSimpleName();
                    List<Shield.Position> positions = new ArrayList<>();
                    d = random.nextDouble();
                    if (d < 0.5) {
                        chargeTincture = fieldTincture;
                    } else {
                        if (ordinaryTincture.isColor())
                            chargeTincture = pickNonColor(random);
                        else if (ordinaryTincture.isMetal())
                            chargeTincture = pickNonMetal(random);
                        else
                            chargeTincture = pickAnyTinctureBut(random, Set.of(fieldTincture, ordinaryTincture));
                    }
                    switch (simpleName) {
                        case "Fess":
                            positions = List.of(
                                    Shield.Position.FESS_DEXTER,
                                    Shield.Position.CENTER,
                                    Shield.Position.FESS_SINISTER
                            );
                            break;
                        case "Chief":
                            positions = List.of(
                                    Shield.Position.CHIEF_DEXTER,
                                    Shield.Position.CHIEF_CENTER,
                                    Shield.Position.CHIEF_SINISTER
                            );
                            break;
                        case "Pale":
                            positions = List.of(
                                    Shield.Position.PALE_TOP,
                                    Shield.Position.CENTER,
                                    Shield.Position.PALE_BOTTOM
                            );
                            break;
                        case "Bend":
                            positions = List.of(
                                    Shield.Position.BEND_TOP_DEXTER,
                                    Shield.Position.CENTER,
                                    Shield.Position.BEND_BOTTOM_SINISTER
                            );
                            break;
                        case "BendSinister":
                            positions = List.of(
                                    Shield.Position.BEND_TOP_SINISTER,
                                    Shield.Position.CENTER,
                                    Shield.Position.BEND_BOTTOM_DEXTER
                            );
                            break;
                        case "Cross":
                            positions = List.of(
                                    Shield.Position.PALE_TOP,
                                    Shield.Position.CENTER,
                                    Shield.Position.PALE_BOTTOM,
                                    Shield.Position.FESS_DEXTER,
                                    Shield.Position.FESS_SINISTER
                            );
                            break;
                        case "Saltire":
                            positions = List.of(
                                    Shield.Position.BEND_TOP_SINISTER,
                                    Shield.Position.CENTER,
                                    Shield.Position.BEND_BOTTOM_DEXTER,
                                    Shield.Position.BEND_TOP_DEXTER,
                                    Shield.Position.BEND_BOTTOM_SINISTER
                            );
                            break;
                        case "Checron":
                        default:
                            positions = List.of();
                    }
                    for (Shield.Position pos : positions)
                        builder.add(chargeFunc, chargeTincture, pos, 1.0);
                }
            } else if (d < 0.55) {
                // a single charge
                MultiFunction<Tincture, Shield.Position, Double, Drawable> func = charges.get(random.nextInt(charges.size()));
                if (fieldTincture == null) {
                    chargeTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                } else if (fieldTincture.isMetal())
                    chargeTincture = pickNonMetal(random);
                else
                    chargeTincture = pickNonColor(random);
                builder.add(func, chargeTincture, Shield.Position.CENTER, 3.0);
            } else {
                // multiple charges.
                MultiFunction<Tincture, Shield.Position, Double, Drawable> func = charges.get(random.nextInt(charges.size()));
                if (fieldTincture == null) {
                    chargeTincture = pickAnyTinctureBut(random, Set.of(t1, t2));
                } else if (fieldTincture.isMetal())
                    chargeTincture = pickNonMetal(random);
                else
                    chargeTincture = pickNonColor(random);
                int ii = random.nextInt(8);
                Shield.Position p1;
                Shield.Position p2;
                Shield.Position p3;
                Shield.Position p4 = null;
                Shield.Position p5 = null;
                switch (ii) {
                    case 1:
                        // in fess
                        p1 = Shield.Position.FESS_DEXTER;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.FESS_SINISTER;
                        break;
                    case 2:
                        // in pale
                        p1 = Shield.Position.PALE_TOP;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.PALE_BOTTOM;
                        break;
                    case 3:
                        // in chief
                        p1 = Shield.Position.CHIEF_DEXTER;
                        p2 = Shield.Position.CHIEF_CENTER;
                        p3 = Shield.Position.CHIEF_SINISTER;
                        break;
                    case 4:
                        // bend
                        p1 = Shield.Position.BEND_TOP_DEXTER;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.BEND_BOTTOM_SINISTER;
                        break;
                    case 5:
                        // bend sinister
                        p1 = Shield.Position.BEND_TOP_SINISTER;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.BEND_BOTTOM_DEXTER;
                        break;
                    case 6:
                        // saltire
                        p1 = Shield.Position.BEND_TOP_SINISTER;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.BEND_BOTTOM_DEXTER;
                        p4 = Shield.Position.BEND_TOP_DEXTER;
                        p5 = Shield.Position.BEND_BOTTOM_SINISTER;
                        break;
                    case 7:
                        // in cross
                        p1 = Shield.Position.PALE_TOP;
                        p2 = Shield.Position.CENTER;
                        p3 = Shield.Position.PALE_BOTTOM;
                        p4 = Shield.Position.FESS_DEXTER;
                        p5 = Shield.Position.FESS_SINISTER;
                        break;
                    default:
                        // default
                        p1 = Shield.Position.HONOR_POINT_DEXTER;
                        p2 = Shield.Position.NAVEL_POINT;
                        p3 = Shield.Position.HONOR_POINT_SINISTER;
                }
                builder.add(func, chargeTincture, p1, 1.0);
                builder.add(func, chargeTincture, p2, 1.0);
                builder.add(func, chargeTincture, p3, 1.0);
                if (ii >= 6) {
                    builder.add(func, chargeTincture, p4, 1.0);
                    builder.add(func, chargeTincture, p5, 1.0);
                }
            }
            d = random.nextDouble();
            if (d < 0.025) {
                // add a border
                Set<Tincture> ft = new HashSet<>();
                if (t1 != null && t2 != null) {
                    ft.add(t1);
                    ft.add(t2);
                } else {
                    ft.add(fieldTincture);
                }
                Tincture borderTincture = pickAnyTinctureBut(random, ft);
                builder.add(new Border(borderTincture));
            }
            d = random.nextDouble();
            if (d < 0.025) {
                // add a border
                Set<Tincture> ft = new HashSet<>();
                if (t1 != null && t2 != null) {
                    ft.add(t1);
                    ft.add(t2);
                } else {
                    ft.add(fieldTincture);
                }
                if (chargeTincture != null ) {
                    ft.add(chargeTincture);
                }
                if (ordinaryTincture != null) {
                    ft.add(ordinaryTincture);
                }
                Tincture borderTincture = pickAnyTinctureBut(random, ft);
                builder.add(new Label(borderTincture));
            }
            builder.build(fw -> {
                try {
                    fw.writeToFile(String.format("%s/shield-%03d", destinationDir, idx.getAndIncrement()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void addCharges(Tincture tincture, Builder builder, MultiFunction<Tincture, Shield.Position, Double, Drawable> func, Shield.Position[] positions) {
        for (Shield.Position position : positions) {
            builder.add(func, tincture, position, 1.0);
        }
    }

    private static Tincture pickAnyTincture(RandomGenerator random) {
        double d = random.nextDouble();
        Tincture t = pickAnyTincture(d);
        System.out.printf("%f %s%n", d, t);
        return t;
    }

    private static Tincture pickAnyTincture(double d) {
        if (d < 0.10)
            return Tincture.OR;
        if (d < 0.20)
            return Tincture.ARGENT;
        if (d < 0.45)
            return Tincture.GULES;
        if (d < 0.70)
            return Tincture.SABLE;
        if (d < 0.875)
            return Tincture.AZURE;
        if (d < 0.90)
            return Tincture.PURPURE;
        if (d < 0.97)
            return Tincture.ERMINE;
        if (d < 0.98)
            return Tincture.ERMINES;
        if (d < 0.99)
            return Tincture.ERMINOIS;
        else
            return Tincture.PEAN;

    }

    private static Tincture pickAnyTinctureBut(RandomGenerator random, Set<Tincture> set) {
        Tincture t = null;
        while (t == null || set.contains(t)) {
            double d = random.nextDouble();
            if (d < 0.10)
                t = Tincture.OR;
            else if (d < 0.20)
                t = Tincture.ARGENT;
            else if (d < 0.45)
                t = Tincture.GULES;
            else if (d < 0.70)
                t = Tincture.SABLE;
            else if (d < 0.875)
                t = Tincture.AZURE;
            else if (d < 0.90)
                t = Tincture.PURPURE;
            else if (d < 0.97)
                t = Tincture.ERMINE;
            else if (d < 0.98)
                t = Tincture.ERMINES;
            else if (d < 0.99)
                t = Tincture.ERMINOIS;
            else
                t = Tincture.PEAN;
        }
        return t;
    }

    private static Tincture pickColor(RandomGenerator random) {
        double d = random.nextDouble();
        if (d < 0.30)
            return Tincture.GULES;
        if (d < 0.60)
            return Tincture.SABLE;
        if (d < 0.80)
            return Tincture.AZURE;
        if (d < 0.975)
            return Tincture.VERT;
        return Tincture.PURPURE;
    }

    private static Tincture pickNonMetal(RandomGenerator random) {
        double d = random.nextDouble();
        if (d < 0.30)
            return Tincture.GULES;
        if (d < 0.55)
            return Tincture.SABLE;
        if (d < 0.70)
            return Tincture.AZURE;
        if (d < 0.90)
            return Tincture.VERT;
        if (d < 0.95)
            return Tincture.PURPURE;
        if (d < 0.98)
            return Tincture.ERMINE;
        if (d < 0.99)
            return Tincture.ERMINES;
        if (d < 0.995)
            return Tincture.ERMINOIS;
        return Tincture.PEAN;
    }

    private static Tincture pickNonColor(RandomGenerator random) {
        double d = random.nextDouble();
        if (d < 0.475)
            return Tincture.OR;
        if (d < 0.95)
            return Tincture.ARGENT;
        if (d < 0.97)
            return Tincture.ERMINE;
        if (d < 0.98)
            return Tincture.ERMINES;
        if (d < 0.99)
            return Tincture.ERMINOIS;
        return Tincture.PEAN;
    }
}