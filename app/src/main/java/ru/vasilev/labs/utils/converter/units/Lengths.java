package ru.vasilev.labs.utils.converter.units;

public enum Lengths implements Unit{
    METER(1), INCH(39.37), FEET(3.281), YARD(1.094);
    private final double value;

    Lengths(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}
