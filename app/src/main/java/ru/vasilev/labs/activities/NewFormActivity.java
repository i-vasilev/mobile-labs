package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.data.Education;
import ru.vasilev.labs.utils.forms.data.Experience;
import ru.vasilev.labs.utils.forms.data.Idable;
import ru.vasilev.labs.utils.forms.data.Industry;

public class NewFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);
        RadioGroup educations = findViewById(R.id.educationRadioGroup);
        for (Education education : Education.values()) {
            RadioButton radioButton = new RadioButton(getApplicationContext());
            radioButton.setText(getString(education.getId()));
            educations.addView(radioButton);
        }
        setAdapter(Experience.values(), R.id.experienceSpinner);
        setAdapter(Industry.values(), R.id.industrySpinner);
    }

    private void setAdapter(Idable[] idableList, int experienceId) {
        String[] experience = (String[]) Arrays.stream(idableList).map(a -> getString(a.getId())).toArray();
        SpinnerAdapter experienceAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.form_name, experience);
        Spinner experienceSpinner = findViewById(experienceId);
        experienceSpinner.setAdapter(experienceAdapter);
    }
}