package ru.vasilev.labs.utils.forms.data;

import ru.vasilev.labs.R;

public enum Education implements Idable {
    INCOMPLETE_SECONDARY(R.string.incompleteSecondary),
    SECONDARY(R.string.secondary),
    HIGH(R.string.high);
    private final int id;

    Education(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
