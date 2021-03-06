package cz.simon.jsonserializer.jsonparser.helpers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeCreator {

    public <T> T createInstanceFor(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public <T> T[] createArrayOfType(Class<T> componentType, int capacity) {
        return (T[]) Array.newInstance(componentType, capacity);
    }

    public <T> ArrayList<T> createArrayListOfType(Class<T> type, int capacity) {
        return new ArrayList<T>(capacity);
    }

    public <T> HashMap<String, T> createHashMapOfType(Class<T> type, int capacity) {
        return new HashMap<>(capacity);
    }

}
