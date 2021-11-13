package ru.vasilev.labs.utils.calculator.operations;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Operations {
    SUM(new SumOperation()),
    DIVIDE(new DivideOperation()),
    DIFFERENCE(new DifferenceOperation()),
    MULTIPLY(new MultiplyOperation());

    private final Operation operation;

    private static final Map<String, Operations> OPERATIONS = new HashMap<>();

    static {
        for (Operations operation :
                values()) {
            OPERATIONS.put(operation.operation.signOperation, operation);
        }
    }

    Operations(Operation operation) {
        this.operation = operation;
    }

    public static Operation valueOfSign(String sign) {
        return Objects.requireNonNull(OPERATIONS.get(sign)).operation;
    }
}
