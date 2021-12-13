package ru.vasilev.labs.utils.forms.data;

import ru.vasilev.labs.R;

public enum Experience implements Idable {
    NO(R.string.noExperience),
    TO1(R.string.from1To3Years),
    FROM1TO3(R.string.from1To3Years),
    FROM3TO6(R.string.from3To6Years),
    FROM6(R.string.from6Years);
    private final int id;

    Experience(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
