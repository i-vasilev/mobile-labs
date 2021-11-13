package ru.vasilev.labs.utils.calculator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.vasilev.labs.utils.calculator.CalculatorExecutor;


public class CalculatorParser {
    public static CalculatorExecutor parse(String phrase) {
        final CalculatorExecutor calculator = new CalculatorExecutor();
        final Matcher match = Pattern.compile("(?:\\(-\\d*\\)|[\\d*])|[+*/-]")
                                     .matcher(phrase);
        while (match.find()) {
            final String expression = match.group();
            Double value = isDouble(expression);
            if (value != null) {
                calculator.addNumber(value);
            } else if (expression.length() == 1) {
                calculator.addOperation(expression);
            } else {
                value = parseDouble(calculator, expression);
                if (value != null) {
                    calculator.addNumber(value);
                } else {

                }
            }
        }
        return calculator;
    }

    private static Double parseDouble(CalculatorExecutor calculator, String expression) {
        Matcher doubleMatch = Pattern.compile("-[\\d]*")
                                     .matcher(expression);
        if (doubleMatch.find()) {
            return isDouble(doubleMatch.group());
        }
        return null;
    }

    private static Double isDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
