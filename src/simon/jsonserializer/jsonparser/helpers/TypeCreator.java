package simon.jsonserializer.jsonparser.helpers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeCreator {

    //region Iterable Creation
    public <T> T[] createArrayOfType(Class<T> componentType, int capacity) {
        return (T[]) Array.newInstance(componentType, capacity);
    }

    public <T> ArrayList<T> createArrayListOfType(Class<T> type, int capacity) {
        return new ArrayList<T>(capacity);
    }

    public <T> HashMap<String, T> createHashMapOfType(Class<T> type, int capacity) {
        return new HashMap<String, T>(capacity);
    }
    //endregion Iterable Creation

}
