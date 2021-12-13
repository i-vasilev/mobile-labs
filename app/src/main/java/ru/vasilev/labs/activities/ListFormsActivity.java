package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.FormsProvider;
import ru.vasilev.labs.utils.forms.data.Form;

public class ListFormsActivity extends AppCompatActivity {
    private List<Form> stringArrayList = FormsProvider.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_forms);
        ArrayAdapter<Object> formArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                                                   R.layout.form_name,
                                                                   stringArrayList.toArray());
        Spinner listForms = findViewById(R.id.listFormsSpinner);
        listForms.setAdapter(formArrayAdapter);
        listForms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showForm(stringArrayList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        findViewById(R.id.backButton).setOnClickListener(this::goBack);
        findViewById(R.id.deleteButton).setOnClickListener(this::delete);
    }

    private void delete(View view) {
        Spinner listForms = findViewById(R.id.listFormsSpinner);
        int selectedItemPosition = listForms.getSelectedItemPosition();
        if (selectedItemPosition >= 0) {
            stringArrayList.remove(selectedItemPosition);
            updateSpinner(listForms);
        }
    }

    private void updateSpinner(Spinner listForms) {
        ArrayAdapter<Object> formArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                                                   R.layout.form_name,
                                                                   stringArrayList.toArray());
        listForms.setAdapter(formArrayAdapter);
    }

    private void goBack(View view) {
        finish();
    }

    protected void showForm(Form form) {
        StringBuilder data = new StringBuilder();
        data.append(form.toString())
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.education))
            .append(getString(form.getEducation()
                                  .getId()))
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.expectedBranchOfWork))
            .append(getString(form.getIndustry()
                                  .getId()))
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.experience));
        TextView formInfo = findViewById(R.id.formInformation);
        formInfo.setText(data.toString());
    }
}