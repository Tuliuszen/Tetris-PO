package Projekt;

import java.util.*;

public class LangPol extends ResourceBundle {
    private static final Map<String, String> contents = new HashMap<>();

    static {
        contents.put("musicButton", "Muzyka Włącz/Wyłącz");
        contents.put("startStopButton", "Start/Stop");
        // Add other keys and translations as needed
    }

    @Override
    protected Object handleGetObject(String key) {
        return contents.get(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(contents.keySet());
    }
}
