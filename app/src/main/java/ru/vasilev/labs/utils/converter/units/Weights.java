package ru.vasilev.labs.utils.converter.units;

import ru.vasilev.labs.utils.converter.annotations.UnitClass;

@UnitClass
public enum Weights implements Unit {
    KG(1, "килограмм"), POUND(2.205, "пуд"), OUNCES(35.274, "унц"), STONE(0.1574, "стон");
    private final double value;
    private final String root;

    Weights(double value, String root) {
        this.value = value;
        this.root = root;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getRoot() {
        return root;
    }
}
