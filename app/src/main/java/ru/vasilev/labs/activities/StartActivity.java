package ru.vasilev.labs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.buttonConverter).setOnClickListener(this::openConverter);
        findViewById(R.id.buttonCalculator).setOnClickListener(this::openCalculator);
        findViewById(R.id.buttonForms).setOnClickListener(this::openForms);
    }

    private void openForms(View view) {
        startActivity(FormsActivity.class);
    }

    private void openCalculator(View view) {
        startActivity(CalculatorActivity.class);
    }

    private void openConverter(View view) {
        startActivity(ConverterActivity.class);
    }

    private void startActivity(Class<?> activityClass) {
        Intent intentConverter = new Intent(getApplicationContext(), activityClass);
        startActivity(intentConverter);
    }
}