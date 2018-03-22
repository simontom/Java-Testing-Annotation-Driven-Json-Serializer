package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {

    private final JsonFieldInformationExtractor fieldInformationExtractor;

    public JsonSerializer(JsonFieldInformationExtractor fieldInformationExtractor) {
        this.fieldInformationExtractor = fieldInformationExtractor;
    }

    public JSONObject serialize(@NotNull Object toBeSerialized) throws JsonSerializationException {
        requireNonNull(toBeSerialized);
        return serializeHelper(toBeSerialized);
    }

    private JSONObject serializeHelper(Object toBeSerialized) throws JsonSerializationException {
        if (toBeSerialized == null) {
            return null;
        }
/*
## PROCESS ARRAY AND LIST
public JSONArray(Collection collection) {
    this.myArrayList = new ArrayList();
    if (collection != null) {
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            this.myArrayList.add(JSONObject.wrap(iter.next()));
        }
    }
}

public JSONArray(Object array) throws JSONException {
    this();
    if (array.getClass().isArray()) {
        int length = Array.getLength(array);
        for (int i = 0; i < length; i += 1) {
            this.put(JSONObject.wrap(Array.get(array, i)));
        }
    } else {
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
}

public JSONObject(Map map) {
    this.map = new HashMap();
    if (map != null) {
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry e = (Map.Entry)i.next();
            Object value = e.getValue();
            if (value != null) {
                this.map.put(e.getKey(), wrap(value));
            }
        }
    }
}
*/
        try {
            JSONObject jsonObject = new JSONObject();

            for (FieldInformation fieldInformation : fieldInformationExtractor.getSerializableFields(toBeSerialized)) {
                tryApplyTypeConverter(fieldInformation);

                if ((fieldInformation.data != null) && !isElementDataPrimitiveType(fieldInformation.data.getClass())) {
                    fieldInformation.data = serializeHelper(fieldInformation.data);
                }

                jsonObject.put(fieldInformation.name, fieldInformation.data);
            }

            return jsonObject;
        }
        catch (Exception e) {
            throw new JsonSerializationException(e.getMessage(), e);
        }
    }

    private boolean isElementDataPrimitiveType(Class<?> fieldDataClass) {
        return (fieldDataClass.isPrimitive()                        ||
                fieldDataClass.isAssignableFrom(String.class)       ||
                fieldDataClass.isAssignableFrom(Boolean.class)      ||
                fieldDataClass.isAssignableFrom(Character.class)    ||
                Number.class.isAssignableFrom(fieldDataClass)       ||
                JSONObject.class.isAssignableFrom(fieldDataClass)   ||
                JSONArray.class.isAssignableFrom(fieldDataClass)    ||
                JSONString.class.isAssignableFrom(fieldDataClass)
        );
    }

    private void tryApplyTypeConverter(FieldInformation fieldInformation) {
        boolean isDataIncluded = (fieldInformation.data != null);
        boolean isDefaultTypeConverter = (fieldInformation.typeConverter instanceof TypeConverter.DEFAULT);

        if (isDataIncluded && !isDefaultTypeConverter) {
            fieldInformation.data = fieldInformation.typeConverter.convertSerialization(fieldInformation.data);
        }
    }

}
