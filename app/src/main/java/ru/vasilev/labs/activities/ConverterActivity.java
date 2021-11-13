package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;
import ru.vasilev.labs.exceptions.IllegalStringToParse;
import ru.vasilev.labs.logger.Event;
import ru.vasilev.labs.logger.EventLogger;
import ru.vasilev.labs.logger.EventType;
import ru.vasilev.labs.utils.converter.parser.StringParser;

public final class ConverterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_converter);

        EditText editValue = findViewById(R.id.editTextValue);
        editValue.setOnEditorActionListener((view, actionId, event) -> {
            int result = actionId & EditorInfo.IME_MASK_ACTION;
            switch (result) {
                case EditorInfo.IME_ACTION_DONE:
                    try {
                        StringParser instance = StringParser.getInstance();
                        instance.setStringToParse(view.getText().toString());
                        EventLogger.print();
                    } catch (IllegalStringToParse illegalStringToParse) {
                    }
                    break;
            }
            return true;
        });

        EventLogger.Companion.add(new Event(EventType.ON_CREATE, "onCreate"));
        EventLogger.Companion.print();
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