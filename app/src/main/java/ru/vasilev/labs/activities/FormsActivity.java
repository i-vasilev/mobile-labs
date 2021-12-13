package ru.vasilev.labs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.FormsProvider;
import ru.vasilev.labs.utils.forms.data.Form;

public class FormsActivity extends AppCompatActivity {
    private List<Form> forms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);
        FormsProvider.setAssetManager(getApplicationContext().getAssets());
        forms = FormsProvider.getInstance();
        updateTotalCountTV();

        findViewById(R.id.listFormsBtn).setOnClickListener(this::openListForms);
        findViewById(R.id.statisticBtn).setOnClickListener(this::openStat);
        findViewById(R.id.newFormBtn).setOnClickListener(this::openNewForm);
    }

    private void openNewForm(View view) {
        Intent intent = new Intent(getApplicationContext(), NewFormActivity.class);
        startActivity(intent);
    }

    private void openStat(View view) {
        Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotalCountTV();
    }

    private void openListForms(View view) {
        Intent intent = new Intent(getApplicationContext(), ListFormsActivity.class);
        startActivity(intent);
    }

    void updateTotalCountTV() {
        TextView view = findViewById(R.id.totalFormsCountTV);
        view.setText(String.format(getResources().getString(R.string.totalCountQuestionnaires), forms.size()));
    }
}
