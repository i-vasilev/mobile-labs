package ru.vasilev.labs.utils.converter;

import android.text.Editable;

import ru.vasilev.labs.exceptions.IllegalStringToParse;
import ru.vasilev.labs.utils.converter.parser.StringParser;
import ru.vasilev.labs.utils.converter.units.Unit;

public class UnitRecord<T extends Unit> {
    private Unit unit;
    private double value;

    public UnitRecord(Unit unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public UnitRecord(String text) throws IllegalStringToParse {
        StringParser instance = StringParser.getInstance();
        instance.setStringToParse(text);
        unit = instance.getUnit();
        value = instance.getValue();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
