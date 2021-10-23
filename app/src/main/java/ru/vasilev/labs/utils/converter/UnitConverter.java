package ru.vasilev.labs.utils.converter;

import ru.vasilev.labs.exceptions.IllegalConvertException;
import ru.vasilev.labs.utils.converter.units.Unit;

public class UnitConverter {
    private static final UnitConverter instance = new UnitConverter();

    private UnitConverter() {
    }

    public <T extends Unit> double convert(UnitRecord<T> unitFrom, T unitTo) throws IllegalConvertException {
        return unitFrom.getValue() / unitFrom.getUnit().getValue() * unitTo.getValue();
    }

    public static UnitConverter getInstance() {
        return instance;
    }
}
