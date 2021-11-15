package ru.vasilev.labs.utils.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ru.vasilev.labs.utils.converter.units.Lengths;
import ru.vasilev.labs.utils.converter.units.Unit;
import ru.vasilev.labs.utils.converter.units.Volume;
import ru.vasilev.labs.utils.converter.units.Weights;

public class AllUnitsGetter {
    private static List<Unit> units = new ArrayList<>();

    public static List<Unit> getAllUnits() {
        units.addAll(Arrays.asList(Weights.values()));
        units.addAll(Arrays.asList(Lengths.values()));
        units.addAll(Arrays.asList(Volume.values()));
        return units;
    }

    public static List<Unit> getUnitsByClass(Class unitClass) {
        List<Unit> units = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(unitClass.getEnumConstants())).forEach(unit -> units.add((Unit) unit));
        return units;
    }

}
