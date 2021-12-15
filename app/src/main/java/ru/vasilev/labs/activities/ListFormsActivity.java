package ru.vasilev.labs.activities;

import android.content.Intent;
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
import ru.vasilev.labs.utils.forms.data.FormConverter;

public class ListFormsActivity extends AppCompatActivity {
    private final List<Form> stringArrayList = FormsProvider.getInstance();
    public final static String FORM_TO_EDIT = "FORM_TO_EDIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_forms);
        updateSpinner();
        findViewById(R.id.backButton).setOnClickListener(this::goBack);
        findViewById(R.id.deleteButton).setOnClickListener(this::delete);
        findViewById(R.id.editButton).setOnClickListener(this::edit);
    }

    private void updateSpinner() {
        ArrayAdapter<Object> formArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                                                   R.layout.form_name,
                                                                   stringArrayList.toArray());
        Spinner listForms = findViewById(R.id.listFormsSpinner);
        listForms.setAdapter(formArrayAdapter);
        listForms.setOnItemSelectedListener(new FormSelectedListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSpinner();
    }

    private void edit(View view) {
        Intent intent = new Intent(getApplicationContext(), NewFormActivity.class);
        Spinner listForms = findViewById(R.id.listFormsSpinner);
        int selectedItemPosition = listForms.getSelectedItemPosition();
        intent.putExtra(FORM_TO_EDIT, selectedItemPosition);
        startActivity(intent);
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

    class FormSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            FormConverter converter = new FormConverter(getApplicationContext());
            TextView formInfo = findViewById(R.id.formInformation);
            formInfo.setText(converter.getString(stringArrayList.get(i)));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}