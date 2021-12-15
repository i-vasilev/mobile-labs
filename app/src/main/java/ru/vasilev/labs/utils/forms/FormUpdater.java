package ru.vasilev.labs.utils.forms;

import ru.vasilev.labs.utils.forms.data.Form;

public interface FormUpdater {
    void updateForm(Form form, Object selectedItem);
}
