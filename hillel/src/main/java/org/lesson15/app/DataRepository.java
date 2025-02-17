package org.lesson15.app;

import java.util.HashMap;
import java.util.Map;

public class DataRepository {
    public static Map<Integer, String> getData() {
        Map<Integer, String> map = new HashMap<>();
        map.put(387, "Lucy");
        map.put(231, "Alice");
        map.put(394, "Bob");
        map.put(172, "Tom");
        return map;
    }
}
