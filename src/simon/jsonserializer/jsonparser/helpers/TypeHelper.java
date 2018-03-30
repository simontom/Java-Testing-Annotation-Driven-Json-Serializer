package simon.jsonserializer.jsonparser.helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeHelper {

    //region Composed Checking
    public boolean isDataPrimitiveJson(Object fieldData) {
        return isTypeJSONArray(fieldData.getClass()) ||
                isTypeJSONObject(fieldData.getClass());
    }

    public boolean isDataPrimitive(Object fieldData) {
        return fieldData == null ||
                isTypeString(fieldData.getClass()) ||
                isTypeBoolean(fieldData.getClass()) ||
                isTypeCharacter(fieldData.getClass()) ||
                isTypeNumber(fieldData.getClass());
    }

    public boolean isTypeIterable(Class<?> clazz) {
        return isTypeArray(clazz) ||
                isTypeArrayList(clazz) ||
                isTypeHashMap(clazz);
    }
    //endregion Composed Checking

    //region Json Checking
    public boolean isTypeJSONObject(Class<?> clazz) {
        return JSONObject.class.isAssignableFrom(clazz);
    }

    public boolean isTypeJSONArray(Class<?> clazz) {
        return JSONArray.class.isAssignableFrom(clazz);
    }
    //endregion Json Checking

    //region String, Boolean, Character
    public boolean isTypeString(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    public boolean isTypeCharacter(Class<?> clazz) {
        return clazz.isAssignableFrom(Character.class) ||
                clazz.isAssignableFrom(Character.TYPE);
    }

    public boolean isTypeBoolean(Class<?> clazz) {
        return clazz.isAssignableFrom(Boolean.class) ||
                clazz.isAssignableFrom(Boolean.TYPE);
    }
    //endregion String, Boolean, Character

    //region Number Checking
    public boolean isTypeNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    public boolean isTypeByte(Class<?> clazz) {
        return clazz.isAssignableFrom(Byte.class) ||
                clazz.isAssignableFrom(Byte.TYPE);
    }

    public boolean isTypeShort(Class<?> clazz) {
        return clazz.isAssignableFrom(Short.class) ||
                clazz.isAssignableFrom(Short.TYPE);
    }

    public boolean isTypeInteger(Class<?> clazz) {
        return clazz.isAssignableFrom(Integer.class) ||
                clazz.isAssignableFrom(Integer.TYPE);
    }

    public boolean isTypeLong(Class<?> clazz) {
        return clazz.isAssignableFrom(Long.class) ||
                clazz.isAssignableFrom(Long.TYPE);
    }

    public boolean isTypeFloat(Class<?> clazz) {
        return clazz.isAssignableFrom(Float.class) ||
                clazz.isAssignableFrom(Float.TYPE);
    }

    public boolean isTypeDouble(Class<?> clazz) {
        return clazz.isAssignableFrom(Double.class) ||
                clazz.isAssignableFrom(Double.TYPE);
    }
    //endregion Number Checking

    //region Iterable Checking
    public boolean isTypeArray(Class<?> clazz) {
        return clazz.isArray();
    }

    public boolean isTypeArrayList(Class<?> clazz) {
        return ArrayList.class.isAssignableFrom(clazz);
    }

    public boolean isTypeHashMap(Class<?> clazz) {
        return HashMap.class.isAssignableFrom(clazz);
    }
    //endregion Iterable Checking

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
