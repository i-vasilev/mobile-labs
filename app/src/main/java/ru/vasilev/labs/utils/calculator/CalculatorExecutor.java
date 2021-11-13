package ru.vasilev.labs.utils.calculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ru.vasilev.labs.utils.calculator.operations.Operation;
import ru.vasilev.labs.utils.calculator.operations.Operations;

public class CalculatorExecutor {
    private final List<Double> numbers = new ArrayList<>();
    private final List<Operation> operations = new ArrayList<>();

    public void addOperation(String operationSign) {
        operations.add(Operations.valueOfSign(operationSign));
    }

    public void addNumber(Double number) {
        numbers.add(number);
    }

    public double calculate() {
        Operation oper;
        while (operations.size() > 0) {
            oper = operations.stream().filter(op -> op.getPriority() == getMinOperationPriority()).findFirst().orElse(null);
            int i = operations.indexOf(oper);
            if (oper.getCountAttributes() == 1) {
                oper.addAttribute(numbers.get(i));
                numbers.set(i, oper.execute());
            } else {
                for (int j = i; j < i + oper.getCountAttributes(); j++) {
                    oper.addAttribute(numbers.get(j));
                }
                numbers.set(i, oper.execute());
                for (int j = i + 1; j < i + oper.getCountAttributes(); j++) {
                    numbers.remove(j);
                }
            }
            operations.remove(i);
        }
        return numbers.get(0);
    }

    private int getMinOperationPriority() {
        return operations.stream().min(Comparator.comparingInt(Operation::getPriority)).get().getPriority();
    }
}
