package simon.jsonserializer.serializer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.Collection;
import java.util.Map;

public class TypeChecker {
    boolean isElementDataPrimitive(Object fieldData) {
        return (fieldData == null ||
                fieldData.getClass().isPrimitive()                        ||
                fieldData.getClass().isAssignableFrom(String.class)       ||
                fieldData.getClass().isAssignableFrom(Boolean.class)      ||
                fieldData.getClass().isAssignableFrom(Character.class)    ||
                Number.class.isAssignableFrom(fieldData.getClass())       ||
                JSONObject.class.isAssignableFrom(fieldData.getClass())   ||
                JSONArray.class.isAssignableFrom(fieldData.getClass())    ||
                JSONString.class.isAssignableFrom(fieldData.getClass())
        );
    }

    boolean isArray(Object fieldData) {
        return (fieldData != null) && fieldData.getClass().isArray();
    }

    boolean isCollection (Object fieldData) {
        return (fieldData != null) && Collection.class.isAssignableFrom(fieldData.getClass());
    }

    boolean isMap (Object fieldData) {
        return  (fieldData != null) && Map.class.isAssignableFrom(fieldData.getClass());
    }
}
