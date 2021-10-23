package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.stream.Collectors;

import ru.vasilev.labs.R;
import ru.vasilev.labs.exceptions.IllegalConvertException;
import ru.vasilev.labs.exceptions.IllegalStringToParse;
import ru.vasilev.labs.logger.Event;
import ru.vasilev.labs.logger.EventLogger;
import ru.vasilev.labs.logger.EventType;
import ru.vasilev.labs.utils.converter.AllUnitsGetter;
import ru.vasilev.labs.utils.converter.UnitConverter;
import ru.vasilev.labs.utils.converter.UnitRecord;
import ru.vasilev.labs.utils.converter.units.Unit;
import ru.vasilev.labs.utils.parser.StringParser;

public final class MainActivity extends AppCompatActivity {

    private UnitRecord unitRecord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        EditText editValue = findViewById(R.id.editTextValue);
        Button conv1 = findViewById(R.id.convert_1);
        Button conv2 = findViewById(R.id.convert_2);
        Button conv3 = findViewById(R.id.convert_3);
        editValue.setOnKeyListener(this::keyTextListener);

        conv1.setOnClickListener(this::onClickConvertBtn);
        conv2.setOnClickListener(this::onClickConvertBtn);
        conv3.setOnClickListener(this::onClickConvertBtn);

        EventLogger.Companion.add(new Event(EventType.ON_CREATE, "onCreate"));
        EventLogger.Companion.print();
    }

    private boolean keyTextListener(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER) {
            EditText editText = (EditText) view;
            Button conv1 = findViewById(R.id.convert_1);
            Button conv2 = findViewById(R.id.convert_2);
            Button conv3 = findViewById(R.id.convert_3);
            try {
                unitRecord = new UnitRecord(editText.getText().toString());
                List<Unit> unitsByClass = AllUnitsGetter
                        .getUnitsByClass(unitRecord.getUnit()
                                .getClass())
                        .stream()
                        .filter(a -> !a.equals(unitRecord.getUnit()))
                        .collect(Collectors.toList());
                conv1.setText(unitsByClass.get(0).getRoot());
                conv1.setEnabled(true);
                conv2.setText(unitsByClass.get(1).getRoot());
                conv2.setEnabled(true);
                conv3.setText(unitsByClass.get(2).getRoot());
                conv3.setEnabled(true);
            } catch (IllegalStringToParse illegalStringToParse) {
                TextView viewById = findViewById(R.id.resultText);
                viewById.setText(R.string.start_string);
                conv1.setText(getString(R.string.empty_value));
                conv1.setEnabled(false);
                conv2.setText(getString(R.string.empty_value));
                conv2.setEnabled(false);
                conv3.setText(getString(R.string.empty_value));
                conv3.setEnabled(false);
            }
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

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EventLogger.Companion.add(new Event(EventType.ON_RESTORE_INSTANCE_STATE, "onRestoreInstanceState"));
        EventLogger.Companion.print();
    }

    protected void onRestart() {
        super.onRestart();

        EventLogger.Companion.add(new Event(EventType.ON_RESTART, "onRestart"));
        EventLogger.Companion.print();
    }

    protected void onStart() {
        super.onStart();

        EventLogger.Companion.add(new Event(EventType.ON_START, "onStart"));
        EventLogger.Companion.print();
    }

    protected void onResume() {
        super.onResume();

        EventLogger.Companion.add(new Event(EventType.ON_RESUME, "onResume"));
        EventLogger.Companion.print();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        EventLogger.Companion.add(new Event(EventType.ON_SAVE_INSTANCE_STATE, "onSaveInstanceState"));
        EventLogger.Companion.print();
    }

    protected void onPause() {
        super.onPause();

        EventLogger.Companion.add(new Event(EventType.ON_PAUSE, "onPause"));
        EventLogger.Companion.print();
    }

    protected void onStop() {
        super.onStop();

        EventLogger.Companion.add(new Event(EventType.ON_STOP, "onStop"));
        EventLogger.Companion.print();
    }

    protected void onDestroy() {
        super.onDestroy();

        EventLogger.Companion.add(new Event(EventType.ON_DESTROY, "onDestroy"));
        EventLogger.Companion.print();
        EventLogger.Companion.clear();
    }
}