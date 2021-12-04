package ru.vasilev.labs.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.calculator.CalculatorExecutor;

public class CalculatorActivity extends AppCompatActivity {
    private boolean isOperationClicked = true;
    CalculatorExecutor executor = new CalculatorExecutor();
    StringBuilder number = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViewById(R.id.d1Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d2Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d3Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d4Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d5Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d6Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d7Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d8Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d9Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.d0Button).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.pmButton).setOnClickListener(this::clickButtonDigitCalculator);
        findViewById(R.id.divideButton).setOnClickListener(this::clickButtonOperationCalculator);
        findViewById(R.id.multButton).setOnClickListener(this::clickButtonOperationCalculator);
        findViewById(R.id.plusButton).setOnClickListener(this::clickButtonOperationCalculator);
        findViewById(R.id.minusButton).setOnClickListener(this::clickButtonOperationCalculator);
        findViewById(R.id.equalsButton).setOnClickListener(this::clickButtonEqualsCalculator);
        findViewById(R.id.clrButton).setOnClickListener(this::clickButtonClearCalculator);
        findViewById(R.id.delButton).setOnClickListener(this::clickButtonDeleteCalculator);
        findViewById(R.id.pmButton).setOnClickListener(this::clickButtonFirstOperationCalculator);
    }

    private void clickButtonFirstOperationCalculator(View view) {
        if (number.toString().startsWith("-")) {
            number = new StringBuilder(number.substring(1));
        } else {
            number.insert(0, "-");
        }
        updateExpressionText();
    }

    private void clickButtonDeleteCalculator(View view) {
        number.setLength(number.length() - 1);
        updateExpressionText();
    }

    private void clickButtonClearCalculator(View view) {
        executor = new CalculatorExecutor();
        number = new StringBuilder();
        updateExpressionText();
    }

    private void clickButtonEqualsCalculator(View view) {
        try {
            executor.addNumber(Double.parseDouble(String.valueOf(number)));
        } catch (Exception ignore) {
            //ignore
        }
        number = new StringBuilder();
        TextView editText = findViewById(R.id.mainTextEdit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Double result = executor.calculate();
            editText.setText(
                    String.format(
                            "%s=%." + (String.valueOf(result % 1).length() - 3) + "f",
                            executor.toString(),
                            result));
        }
    }

    private void clickButtonDigitCalculator(View view) {
        number.append(view.getTag());
        updateExpressionText();
        isOperationClicked = false;
    }

    private void updateExpressionText() {
        TextView editText = findViewById(R.id.mainTextEdit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editText.setText(String.format("%s%s", executor.toString(), number));
        }
    }

    private void clickButtonOperationCalculator(View view) {
        if (!isOperationClicked) {
            executor.addNumber(Double.parseDouble(String.valueOf(number)));
            number = new StringBuilder();
            executor.addOperation((String) view.getTag());
            updateExpressionText();
            isOperationClicked = true;
        }
    }
}