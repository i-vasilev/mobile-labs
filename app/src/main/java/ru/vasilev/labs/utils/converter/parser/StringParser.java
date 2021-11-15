package ru.vasilev.labs.utils.converter.parser;

import java.util.List;

import ru.vasilev.labs.exceptions.IllegalStringToParse;
import ru.vasilev.labs.utils.converter.AllUnitsGetter;
import ru.vasilev.labs.utils.converter.units.Unit;

public class StringParser {
    private final static StringParser instance = new StringParser();
    private int value;
    private Unit unit;
    private Class unitClass;

    public void setStringToParse(String stringToParse) throws IllegalStringToParse {
        String[] s = stringToParse.split(" ");
        if (s.length == 2) {
            value = Integer.parseInt(s[0]);
            for (Unit unit :
                    AllUnitsGetter.getAllUnits()) {
                if (s[1].matches(String.format("^%s[а-я]{0,2}$", unit.getRoot()))) {
                    unitClass = unit.getClass();
                    this.unit = unit;
                    return;
                }
            }
        }
        throw new IllegalStringToParse(stringToParse);
    }

    public int getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    public List<Unit> getUnits() {
        return AllUnitsGetter.getUnitsByClass(unitClass);
    }

    public static StringParser getInstance() {
        return instance;
    }
}
