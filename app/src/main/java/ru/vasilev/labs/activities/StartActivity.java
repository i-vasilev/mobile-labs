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
    }

    private void openCalculator(View view) {
        Intent intentCalculator = new Intent(getApplicationContext(), CalculatorActivity.class);
        startActivity(intentCalculator);
    }

    private void openConverter(View view) {
        Intent intentConverter = new Intent(getApplicationContext(), ConverterActivity.class);
        startActivity(intentConverter);
    }
}