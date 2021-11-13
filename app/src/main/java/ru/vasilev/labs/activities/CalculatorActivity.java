package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;

public class CalculatorActivity extends AppCompatActivity {
    StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViewById(R.id.buttonConverter).setOnClickListener(this::clickButtonCalculator);
    }

    private void clickButtonCalculator(View view) {
        expression.append(view.getTag());
        TextView editText = findViewById(R.id.mainTextEdit);
        editText.setText(expression);
    }
}