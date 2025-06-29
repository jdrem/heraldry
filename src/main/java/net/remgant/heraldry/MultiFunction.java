package net.remgant.heraldry;

@FunctionalInterface
public interface MultiFunction<A,B,C,D> {
    D apply(A a, B b, C c);
}
