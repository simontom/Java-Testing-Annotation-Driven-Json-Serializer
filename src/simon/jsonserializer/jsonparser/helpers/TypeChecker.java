package simon.jsonserializer.jsonparser.helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeChecker {

    //region Composed Checking
    public boolean isTypePrimitiveJson(Class<?> clazz) {
        return isTypeJSONArray(clazz) ||
                isTypeJSONObject(clazz);
    }

    public boolean isTypePrimitive(Class<?> clazz) {
        return isTypeString(clazz) ||
                isTypeBoolean(clazz) ||
                isTypeChar(clazz) ||
                isTypeNumber(clazz);
    }

    public boolean isDataPrimitive(Object fieldData) {
        return fieldData == null ||
                isTypePrimitive(fieldData.getClass());
    }

    public boolean isTypeIterable(Class<?> clazz) {
        return isTypeArray(clazz) ||
                isTypeArrayList(clazz) ||
                isTypeHashMap(clazz);
    }
    //endregion Composed Checking

    //region Json
    public boolean isTypeJSONObject(Class<?> clazz) {
        return JSONObject.class.isAssignableFrom(clazz);
    }

    public boolean isTypeJSONArray(Class<?> clazz) {
        return JSONArray.class.isAssignableFrom(clazz);
    }
    //endregion Json

    //region String, Boolean, Character
    public boolean isTypeString(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    public boolean isTypeChar(Class<?> clazz) {
        return clazz.isAssignableFrom(Character.class) ||
                clazz.isAssignableFrom(Character.TYPE);
    }

    public boolean isTypeBoolean(Class<?> clazz) {
        return clazz.isAssignableFrom(Boolean.class) ||
                clazz.isAssignableFrom(Boolean.TYPE);
    }
    //endregion String, Boolean, Character

    //region Number
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
    //endregion Number

    //region Iterable
    public boolean isTypeArray(Class<?> clazz) {
        return clazz.isArray();
    }

    public boolean isTypeArrayList(Class<?> clazz) {
        return ArrayList.class.isAssignableFrom(clazz);
    }

    public boolean isTypeHashMap(Class<?> clazz) {
        return HashMap.class.isAssignableFrom(clazz);
    }

    public Class<?> getIterableItemClass(Field field) {
        Class<?> clazz = field.getType().getComponentType();
        if (clazz != null) {
            return clazz;
        }

        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        if (parameterizedType.getActualTypeArguments().length == 1) {
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return (Class<?>) parameterizedType.getActualTypeArguments()[1];
    }

    public Class<?> getArrayItemClass(Field field) {
        return field.getType().getComponentType();
    }

    public Class<?> getArrayListItemClass(Field field) {
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    public Class<?> getHashMapItemClass(Field field) {
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return (Class<?>) parameterizedType.getActualTypeArguments()[1];
    }
    //endregion Iterable

}
