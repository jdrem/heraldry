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

import java.util.*;
import java.util.function.Function;
import java.util.random.RandomGenerator;

public abstract class DrawRandom {
    static Function<Tincture, Drawable> bend = Bend::new;
    static Function<Tincture, Drawable> bendSinister = BendSinister::new;
    static Function<Tincture, Drawable> chenvron = Chevron::new;
    static Function<Tincture, Drawable> chief = Chief::new;
    static Function<Tincture, Drawable> cross = Cross::new;
    static Function<Tincture, Drawable> fess = Fess::new;
    static Function<Tincture, Drawable> pale = Pale::new;
    static Function<Tincture, Drawable> saltire = Saltire::new;
    static Function<Tincture, Drawable> flanches = Flanches::new;

    final static List<Function<Tincture, Drawable>> ordinaries = List.of(
            bend,
            bendSinister,
            chenvron,
            chief,
            cross,
            fess,
            pale,
            saltire,
            flanches
    );

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
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> mascle = Mascle::new;
    final static MultiFunction<Tincture, Shield.Position, Double, Drawable> escutcheon = Escutcheon::new;


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
            star,
            mascle,
            escutcheon
    );


    abstract public void run(String[] args);

    public void addCharges(Tincture tincture, Builder builder, MultiFunction<Tincture, Shield.Position, Double, Drawable> func, Shield.Position[] positions) {
        for (Shield.Position position : positions) {
            builder.add(func, tincture, position, 1.0);
        }
    }

    protected static Tincture pickAnyTincture(RandomGenerator random) {
        double d = random.nextDouble();
        return pickAnyTincture(d);
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

    protected static Tincture pickAnyTinctureBut(RandomGenerator random, Set<Tincture> set) {
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

    protected static Tincture pickNonMetal(RandomGenerator random) {
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

    protected static Tincture pickNonColor(RandomGenerator random) {
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