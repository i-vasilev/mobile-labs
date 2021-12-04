package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.stream.Collectors;

import ru.vasilev.labs.R;
import ru.vasilev.labs.exceptions.IllegalConvertException;
import ru.vasilev.labs.exceptions.IllegalStringToParse;
//import ru.vasilev.labs.logger.Event;
//import ru.vasilev.labs.logger.EventLogger;
//import ru.vasilev.labs.logger.EventType;
import ru.vasilev.labs.utils.converter.AllUnitsGetter;
import ru.vasilev.labs.utils.converter.UnitConverter;
import ru.vasilev.labs.utils.converter.UnitRecord;
import ru.vasilev.labs.utils.converter.parser.StringParser;
import ru.vasilev.labs.utils.converter.units.Unit;

public final class ConverterActivity extends AppCompatActivity {

    private UnitRecord unitRecord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_converter);

        EditText editValue = findViewById(R.id.editTextValue);
        Button conv1 = findViewById(R.id.convert_1);
        Button conv2 = findViewById(R.id.convert_2);
        Button conv3 = findViewById(R.id.convert_3);
        editValue.setOnKeyListener(this::keyTextListener);

        conv1.setOnClickListener(this::onClickConvertBtn);
        conv2.setOnClickListener(this::onClickConvertBtn);
        conv3.setOnClickListener(this::onClickConvertBtn);

//        EventLogger.Companion.add(new Event(EventType.ON_CREATE, "onCreate"));
//        EventLogger.Companion.print();
    }

    private boolean keyTextListener(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER) {
            EditText editText = (EditText) view;
            Button conv1 = findViewById(R.id.convert_1);
            Button conv2 = findViewById(R.id.convert_2);
            Button conv3 = findViewById(R.id.convert_3);
            boolean isEnabled = true;
            try {
                unitRecord = new UnitRecord(editText.getText().toString());
                List<Unit> unitsByClass = AllUnitsGetter
                        .getUnitsByClass(unitRecord.getUnit()
                                .getClass())
                        .stream()
                        .filter(a -> !a.equals(unitRecord.getUnit()))
                        .collect(Collectors.toList());
                conv1.setText(unitsByClass.get(0).getRoot());
                conv2.setText(unitsByClass.get(1).getRoot());
                conv3.setText(unitsByClass.get(2).getRoot());
            } catch (IllegalStringToParse illegalStringToParse) {
                isEnabled = false;
                TextView viewById = findViewById(R.id.resultText);
                viewById.setText(R.string.start_string);
                conv1.setText(getString(R.string.empty_value));
                conv2.setText(getString(R.string.empty_value));
                conv3.setText(getString(R.string.empty_value));
            }
            conv1.setEnabled(isEnabled);
            conv2.setEnabled(isEnabled);
            conv3.setEnabled(isEnabled);
        }
        return true;
    }


    void onClickConvertBtn(View btn) {
        StringParser instance = StringParser.getInstance();
        List<Unit> unitsByClass = AllUnitsGetter
                .getUnitsByClass(instance.getUnit()
                        .getClass())
                .stream()
                .filter(a -> !a.equals(instance.getUnit()))
                .collect(Collectors.toList());
        Unit unit = unitsByClass.get(Integer.parseInt(String.valueOf(btn.getTag())) - 1);
        try {
            double convertedValue = UnitConverter.getInstance()
                    .convert(unitRecord, unit);
            TextView viewById = findViewById(R.id.resultText);
            viewById.setText(String.valueOf(convertedValue));
        } catch (IllegalConvertException e) {
            e.printStackTrace();
        }
    }
}