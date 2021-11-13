package ru.vasilev.labs.utils.calculator.operations;

public class MultiplyOperation extends Operation {
    public MultiplyOperation() {
        priority = 1;
        countAttributes = 2;
        signOperation = "*";
    }

    @Override
    public Double execute() {
        final double v = attributes.get(0) * attributes.get(1);
        clearAttributes();
        return v;
    }
}
