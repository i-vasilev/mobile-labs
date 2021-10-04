package ru.vasilev.labs.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.vasilev.labs.R;
import ru.vasilev.labs.logger.Event;
import ru.vasilev.labs.logger.EventLogger;
import ru.vasilev.labs.logger.EventType;

public final class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        EditText editValue = findViewById(R.id.editTextValue);
        editValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
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