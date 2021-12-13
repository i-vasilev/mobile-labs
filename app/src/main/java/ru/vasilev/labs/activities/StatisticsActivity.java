package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;
import ru.vasilev.labs.utils.forms.FormsProvider;
import ru.vasilev.labs.utils.forms.ListOfForms;
import ru.vasilev.labs.utils.forms.data.Form;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView text = findViewById(R.id.statTextView);
        ListOfForms forms = FormsProvider.getInstance();

        StringBuilder string = new StringBuilder();
        string.append(String.format(getString(R.string.totalCountForms), forms.size()));
        if (forms.size() > 0) {
            setStatisticText(forms, string);
        }
        text.setText(string);
        findViewById(R.id.goBackBtn).setOnClickListener(this::goBack);
    }

    private void goBack(View view) {
        finish();
    }

    private void setStatisticText(ListOfForms forms, StringBuilder string) {
        string.append(String.format(getString(R.string.totalCountForms), forms.size()));
        string.append(System.getProperty("line.separator"))
              .append(String.format(getString(R.string.mostCommonEducation),
                                    getString(forms.getMostCommon(Form::getEducation)
                                                   .getId())))
              .append(System.getProperty("line.separator"))
              .append(String.format(getString(R.string.mostCommonIndustry),
                                    getString(forms.getMostCommon(Form::getIndustry)
                                                   .getId())))
              .append(System.getProperty("line.separator"))
              .append(String.format(getString(R.string.mostCommonExperience),
                                    getString(forms.getMostCommon(Form::getExperience)
                                                   .getId())))
              .append(System.getProperty("line.separator"))
              .append(String.format(getString(R.string.averagePay),
                                    forms.getAvg(Form::getDesiredPay)))
              .append(System.getProperty("line.separator"))
              .append(String.format(getString(R.string.expectedEmployment),
                                    getString(forms.getMostExpectedEmployee())));
    }

}
