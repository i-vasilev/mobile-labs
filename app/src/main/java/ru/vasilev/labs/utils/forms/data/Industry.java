package ru.vasilev.labs.utils.forms.data;

import ru.vasilev.labs.R;

public enum Industry implements Idable {
    GOVERNMENT(R.string.government),
    EXTRACTIVE(R.string.extractive),
    IT(R.string.it),
    SERVICE(R.string.services);
    private final int id;

    Industry(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
