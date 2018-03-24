package simon.jsonserializer.parser.helpers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.Collection;
import java.util.Map;

public class TypeChecker {
    public boolean isElementDataPrimitive(Object fieldData) {
        return (fieldData == null ||
                fieldData.getClass().isPrimitive() ||
                fieldData.getClass().isAssignableFrom(String.class) ||
                fieldData.getClass().isAssignableFrom(Boolean.class) ||
                fieldData.getClass().isAssignableFrom(Character.class) ||
                Number.class.isAssignableFrom(fieldData.getClass()) ||
                JSONObject.class.isAssignableFrom(fieldData.getClass()) ||
                JSONArray.class.isAssignableFrom(fieldData.getClass()) ||
                JSONString.class.isAssignableFrom(fieldData.getClass())
        );
    }

    public boolean isArray(Object fieldData) {
        return fieldData.getClass().isArray();
    }

    public boolean isCollection(Object fieldData) {
        return Collection.class.isAssignableFrom(fieldData.getClass());
    }

    public boolean isMap(Object fieldData) {
        return Map.class.isAssignableFrom(fieldData.getClass());
    }
}
