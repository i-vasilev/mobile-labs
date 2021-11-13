package ru.vasilev.labs.utils.converter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.classgraph.ClassGraph;
import ru.vasilev.labs.utils.converter.annotations.UnitClass;
import ru.vasilev.labs.utils.converter.units.Unit;

public class AllUnitsGetter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Unit> getAllUnitsInProject() {
        List<Unit> units = new ArrayList<>();
        String packageName = "ru.vasilev";
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        List<Class> classes = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());
        List<Class<?>> s = new ClassGraph()
                .verbose()
                .enableClassInfo()
                .enableAnnotationInfo()
                .scan().getClassesWithAnnotation(UnitClass.class).loadClasses();
        Reflections reflections = new Reflections("ru.vasilev.labs");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(UnitClass.class);
        typesAnnotatedWith.forEach(a ->
                Arrays.stream(Objects.requireNonNull(a.getEnumConstants())).forEach(unit -> units.add((Unit) unit)));
//        new URLClassLoader()
        return units;
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Unit> getUnitsByClass(Class unitClass) {
        List<Unit> units = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(unitClass.getEnumConstants())).forEach(unit -> units.add((Unit) unit));
        return units;
    }

}
