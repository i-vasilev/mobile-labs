package ru.vasilev.labs.utils.converter;

import ru.vasilev.labs.exceptions.IllegalConvertException;
import ru.vasilev.labs.utils.converter.units.Unit;

public class UnitConverter {
    private Unit unitFrom;
    private Unit unitTo;
    private static UnitConverter instance = new UnitConverter();

    private UnitConverter() {
    }

    public <T extends Unit> UnitConverter setUnitFrom(T unitFrom) {
        this.unitFrom = unitFrom;
        return this;
    }

    public <T extends Unit> UnitConverter setUnitTo(T unitTo) {
        this.unitTo = unitTo;
        return this;
    }

    public double convert(double valueUnitFrom) throws IllegalConvertException {
        if (unitFrom.getClass() != unitTo.getClass()) {
            throw new IllegalConvertException(
                    String.format("Illegal using classes. %s and %s are incompatible.",
                            unitFrom.getClass().getCanonicalName(),
                            unitTo.getClass().getCanonicalName()));
        }
        return valueUnitFrom / unitFrom.getValue() * unitTo.getValue();
    }

    public static UnitConverter getInstance() {
        return instance;
    }
}
