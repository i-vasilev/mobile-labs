package ru.vasilev.labs.utils.converter.units;

import ru.vasilev.labs.utils.converter.annotations.UnitClass;

@UnitClass
public enum Lengths implements Unit{
    METER(1, "метр"), INCH(39.37, "дюйм"), FEET(3.281, "фут"), YARD(1.094, "ярд");
    private final double value;
    private final String root;

    Lengths(double value, String root) {
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
