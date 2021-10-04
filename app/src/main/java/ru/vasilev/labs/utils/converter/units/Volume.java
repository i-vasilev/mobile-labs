package ru.vasilev.labs.utils.converter.units;

import ru.vasilev.labs.utils.converter.annotations.UnitClass;

@UnitClass
public enum Volume implements Unit {
    LITRE(1, "литр"), GALLON(0.264172, "галлон"), QUART(1.05669, "кварт"), PINT(2.113, "пинт");
    private final double value;
    private final String root;

    Volume(double value, String root) {
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
