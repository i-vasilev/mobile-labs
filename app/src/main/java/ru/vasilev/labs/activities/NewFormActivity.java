package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.FormUpdater;
import ru.vasilev.labs.utils.forms.FormsProvider;
import ru.vasilev.labs.utils.forms.data.Education;
import ru.vasilev.labs.utils.forms.data.Experience;
import ru.vasilev.labs.utils.forms.data.Form;
import ru.vasilev.labs.utils.forms.data.Idable;
import ru.vasilev.labs.utils.forms.data.Industry;
import ru.vasilev.labs.utils.forms.data.StringWithTag;

public class NewFormActivity extends AppCompatActivity {
    private Form form = new Form();
    private boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);
        RadioGroup educations = findViewById(R.id.educationRadioGroup);
        for (Education education : Education.values()) {
            RadioButton radioButton = new RadioButton(getApplicationContext());
            radioButton.setText(getString(education.getId()));
            radioButton.setTag(education);
            educations.addView(radioButton);
        }
        setAdapter(Experience.values(), R.id.experienceSpinner, (a, b) -> a.setExperience((Experience) b));
        setAdapter(Industry.values(), R.id.industrySpinner, (a, b) -> a.setIndustry((Industry) b));
        SeekBar payLevelSeekBar = findViewById(R.id.payLevelSeekBar);
        payLevelSeekBar.setOnSeekBarChangeListener(new PayLevelSeekBarChangeListener());
        findViewById(R.id.saveButton).setOnClickListener(this::save);
        if (getIntent().getExtras() != null) {
            Object o = getIntent().getExtras()
                                  .get(ListFormsActivity.FORM_TO_EDIT);
            if (o instanceof Integer) {
                form = FormsProvider.getInstance().get((Integer) o);
                setForm();
                isEditing = true;
            }
        }
    }

    private void setForm() {
        EditText editText = findViewById(R.id.familyNameEditText);
        editText.setText(form.getFamilyName());
        editText = findViewById(R.id.nameEditText);
        editText.setText(form.getFirstName());
        editText = findViewById(R.id.surnameEditText);
        editText.setText(form.getSecondName());
        editText = findViewById(R.id.additionalInformationEditText);
        editText.setText(form.getAdditionalInformation());
        RadioGroup educationRadioGroup = (RadioGroup) findViewById(R.id.educationRadioGroup);
        for (int i = 0; i < educationRadioGroup.getChildCount(); i++) {
            RadioButton ed = (RadioButton) educationRadioGroup.getChildAt(i);
            if (ed.getTag() == form.getEducation()) {
                ed.setChecked(true);
                break;
            }
        }
        CheckBox checkBox = (CheckBox) findViewById(R.id.partTimeCheckBox);
        checkBox.setChecked(form.isPartTime());
        checkBox = (CheckBox) findViewById(R.id.fullTimeCheckBox);
        checkBox.setChecked(form.isFullTime());
        checkBox = (CheckBox) findViewById(R.id.internshipCheckBox);
        checkBox.setChecked(form.isInternship());
        checkBox = (CheckBox) findViewById(R.id.oneTimeCheckBox);
        checkBox.setChecked(form.isOnetime());
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.noPhysicalLimitationsToggle);
        toggleButton.setChecked(form.isHasPhysicalLimitations());
        toggleButton = (ToggleButton) findViewById(R.id.criminalToggle);
        toggleButton.setChecked(form.isHasCriminalRecord());
        SeekBar seekBar = findViewById(R.id.payLevelSeekBar);
        seekBar.setProgress(form.getDesiredPay());
        Spinner spinner = findViewById(R.id.experienceSpinner);
        spinner.setSelection(Arrays.asList(Experience.values())
                                   .indexOf(form.getExperience()));
        spinner = findViewById(R.id.industrySpinner);
        spinner.setSelection(Arrays.asList(Industry.values())
                                   .indexOf(form.getIndustry()));
    }

    private void save(View view) {
        if (((Switch) findViewById(R.id.aggreementAndConfirmationSwitch)).isChecked()) {
            EditText editText = findViewById(R.id.familyNameEditText);
            form.setFamilyName(editText.getText()
                                       .toString());
            editText = findViewById(R.id.nameEditText);
            form.setFirstName(editText.getText()
                                      .toString());
            editText = findViewById(R.id.surnameEditText);
            form.setSecondName(editText.getText()
                                       .toString());
            editText = findViewById(R.id.additionalInformationEditText);
            form.setAdditionalInformation(editText.getText()
                                                  .toString());
            RadioGroup educationRadioGroup = (RadioGroup) findViewById(R.id.educationRadioGroup);
            Education education = (Education) findViewById(educationRadioGroup.getCheckedRadioButtonId()).getTag();
            form.setEducation(education);
            form.setPartTime(((CheckBox) findViewById(R.id.partTimeCheckBox)).isChecked());
            form.setFullTime(((CheckBox) findViewById(R.id.fullTimeCheckBox)).isChecked());
            form.setInternship(((CheckBox) findViewById(R.id.internshipCheckBox)).isChecked());
            form.setOnetime(((CheckBox) findViewById(R.id.oneTimeCheckBox)).isChecked());
            form.setHasPhysicalLimitations(((ToggleButton) findViewById(R.id.noPhysicalLimitationsToggle)).isChecked());
            form.setHasCriminalRecord(((ToggleButton) findViewById(R.id.criminalToggle)).isChecked());
            if (!isEditing) {
                FormsProvider.getInstance()
                             .add(form);
            }
            finish();
        }
    }

    private void setAdapter(Idable[] idableList, int experienceId, FormUpdater r) {
        Object[] experience = Arrays.stream(idableList)
                                    .map(a -> new StringWithTag(getString(a.getId()), a))
                                    .toArray();
        SpinnerAdapter experienceAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.form_name, experience);
        Spinner experienceSpinner = findViewById(experienceId);
        experienceSpinner.setAdapter(experienceAdapter);
        experienceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r.updateForm(form, ((StringWithTag) adapterView.getSelectedItem()).tag);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class PayLevelSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            ((TextView) findViewById(R.id.payLevelTextView)).setText(String.valueOf(i));
            form.setDesiredPay(i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
