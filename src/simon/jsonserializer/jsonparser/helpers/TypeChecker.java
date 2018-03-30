package simon.jsonserializer.jsonparser.helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeChecker {

    public boolean isDataJson(Object fieldData) {
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

    public boolean isTypeJSONObject(Class<?> clazz) {
        return JSONObject.class.isAssignableFrom(clazz);
    }

    public boolean isTypeJSONArray(Class<?> clazz) {
        return JSONArray.class.isAssignableFrom(clazz);
    }

    public boolean isTypeString(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    public boolean isTypeBoolean(Class<?> clazz) {
        return clazz.isAssignableFrom(Boolean.class) ||
                clazz.isAssignableFrom(Boolean.TYPE);
    }

    public boolean isTypeCharacter(Class<?> clazz) {
        return clazz.isAssignableFrom(Character.class) ||
                clazz.isAssignableFrom(Character.TYPE);
    }

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

    public boolean isArray(Object fieldData) {
        return fieldData.getClass().isArray();
    }

    public boolean isArrayList(Object fieldData) {
        return ArrayList.class.isAssignableFrom(fieldData.getClass());
    }

    public boolean isHashMap(Object fieldData) {
        return HashMap.class.isAssignableFrom(fieldData.getClass());
    }

}
