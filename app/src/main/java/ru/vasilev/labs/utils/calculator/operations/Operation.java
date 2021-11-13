package ru.vasilev.labs.utils.calculator.operations;

import java.util.ArrayList;
import java.util.List;

import ru.vasilev.labs.utils.calculator.exceptions.IllegalOperationAttributesCount;

public abstract class Operation implements OperationExecutor {
    protected int priority;
    protected int countAttributes;
    protected List<Double> attributes = new ArrayList<>();
    protected String signOperation;

    public double doOperation() throws IllegalOperationAttributesCount {
        if (countAttributes != attributes.size()) {
            throw new IllegalOperationAttributesCount("Количество аттрибутов не верное");
        }
        return execute();
    }

    public void addAttribute(Double attribute) {
        attributes.add(attribute);
    }

    public int getPriority() {
        return priority;
    }

    public int getCountAttributes() {
        return countAttributes;
    }

    public String getSignOperation() {
        return signOperation;
    }

    public void clearAttributes() {
        attributes.clear();
    }
}
