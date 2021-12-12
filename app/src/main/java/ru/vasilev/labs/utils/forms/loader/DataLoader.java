package ru.vasilev.labs.utils.forms.loader;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.vasilev.labs.utils.forms.data.Form;

public class DataLoader {
    public static List<Form> load(AssetManager assetManager) throws IOException {
        List<Form> loadForms = new ArrayList<>();
        List<String> jsonFiles = Arrays.stream(assetManager.list(""))
                .filter(a -> a.endsWith(".json"))
                .collect(Collectors.toList());
        for (String fileName :
                jsonFiles) {
            InputStream open = assetManager.open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            loadForms.add(new Gson().fromJson(bufferedReader, Form.class));
        }
        return loadForms;
    }
}
