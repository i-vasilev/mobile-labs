package ru.vasilev.labs.utils.converter.units;

public enum Weights implements Unit {
    KG(1), POUND(2.205), OUNCES(35.274), STONE(0.1574);
    private final double value;

    Weights(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}
