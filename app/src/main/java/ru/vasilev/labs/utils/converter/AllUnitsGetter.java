package ru.vasilev.labs.utils.converter;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ru.vasilev.labs.utils.converter.annotations.UnitClass;
import ru.vasilev.labs.utils.converter.units.Unit;

public class AllUnitsGetter {

    public static List<Unit> getAllUnitsInProject() {
        List<Unit> units = new ArrayList<>();
        Reflections reflections = new Reflections("ru.vasilev.labs");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(UnitClass.class);
        typesAnnotatedWith.forEach(a ->
                Arrays.stream(Objects.requireNonNull(a.getEnumConstants())).forEach(unit -> units.add((Unit) unit)));
        return units;
    }

    public static List<Unit> getUnitsByClass(Class unitClass) {
        List<Unit> units = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(unitClass.getEnumConstants())).forEach(unit -> units.add((Unit) unit));
        return units;
    }

}
