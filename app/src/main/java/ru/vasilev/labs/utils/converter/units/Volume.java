package ru.vasilev.labs.utils.converter.units;

public enum Volume implements Unit {
    LITRE(1), GALLON(0.264172), QUART(1.05669), PINT(2.113);
    private final double value;

    Volume(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}
