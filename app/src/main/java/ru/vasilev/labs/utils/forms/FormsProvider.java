package ru.vasilev.labs.utils.forms;

import android.content.res.AssetManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.vasilev.labs.utils.forms.data.Form;
import ru.vasilev.labs.utils.forms.loader.DataLoader;

public class FormsProvider {
    private static final ListOfForms forms = new ListOfForms();

    private static AssetManager assetManager = null;

    private static boolean isInitialized = false;

    public static void setAssetManager(AssetManager assetManager) {
        FormsProvider.assetManager = assetManager;
    }

    public static ListOfForms getInstance() {
        if (forms.size() == 0 && assetManager != null && !isInitialized) {
            try {
                forms.addAll(DataLoader.load(assetManager));
                isInitialized = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return forms;
    }
}
