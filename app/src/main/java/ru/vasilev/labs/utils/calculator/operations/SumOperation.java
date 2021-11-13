package ru.vasilev.labs.utils.calculator.operations;

public class SumOperation extends Operation {
    public SumOperation() {
        priority = 2;
        countAttributes = 2;
        signOperation = "+";
    }

    @Override
    public Double execute() {
        double v = attributes.get(0) + attributes.get(1);
        clearAttributes();
        return v;
    }
}
