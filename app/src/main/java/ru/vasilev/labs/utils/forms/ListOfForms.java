package ru.vasilev.labs.utils.forms;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.data.Form;

public class ListOfForms extends ArrayList<Form> {
    @NonNull
    public <T> T getMostCommon(Function<Form, T> function) {
        return stream()
                .map(function)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(education -> education, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }


    public Long getMostCommonCount(Function<Form, Boolean> function) {
        return stream()
                .map(function)
                .filter(a->a)
                .collect(Collectors.groupingBy(education -> education, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElse((long) -1);
    }


    public int getAvg(Function<Form, Integer> function) {
        return (int) stream()
                .map(function)
                .mapToDouble(a -> a)
                .average()
                .getAsDouble();
    }


    public Integer getMostExpectedEmployee() {
        Long fullTimeCount = getMostCommonCount(Form::isFullTime);
        Long partTimeCount = getMostCommonCount(Form::isPartTime);
        Long oneTimeCount = getMostCommonCount(Form::isOnetime);
        Long internshipCount = getMostCommonCount(Form::isInternship);
        HashMap<Integer, Long> employments = new HashMap<>();
        employments.put(R.string.fullTime, fullTimeCount);
        employments.put(R.string.partTime, partTimeCount);
        employments.put(R.string.oneTime, oneTimeCount);
        employments.put(R.string.internship, internshipCount);
        Integer mostExpectedEmploye = employments.entrySet()
                                                 .stream()
                                                 .max((a, b) -> Math.toIntExact(
                                                         a.getValue() - b.getValue()))
                                                 .get()
                                                 .getKey();
        return mostExpectedEmploye;
    }
}
