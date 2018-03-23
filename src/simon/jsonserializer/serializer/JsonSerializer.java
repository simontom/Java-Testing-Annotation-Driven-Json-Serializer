package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import simon.jsonserializer.serializer.exceptions.JsonSerializationException;

import static java.util.Objects.requireNonNull;

## Create JsonDeserializer
## Create Facade for easy calling Serialization and Deserialization
public class JsonSerializer {

    private final JsonFieldInformationExtractor fieldInformationExtractor;
    private final TypeChecker typeChecker;

    public JsonSerializer(JsonFieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        this.fieldInformationExtractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
    }

    public JSONObject serialize(@NotNull Object toBeSerialized) throws JsonSerializationException {
        requireNonNull(toBeSerialized);
        return serializeHelper(toBeSerialized);
    }

    private JSONObject serializeHelper(Object toBeSerialized) throws JsonSerializationException {
        if (toBeSerialized == null) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject();

            for (FieldInformation fieldInformation : fieldInformationExtractor.getSerializableFields(toBeSerialized)) {
                fieldInformation.data = fieldInformation.typeConverter.convertSerialization(fieldInformation.data);

                if (typeChecker.isArray(fieldInformation.data)) {
                    fieldInformation.data = serializeArray(fieldInformation.data);
                }
                else if (typeChecker.isCollection(fieldInformation.data)) {
                    fieldInformation.data = serializeCollection(fieldInformation.data);
                }
                else if (typeChecker.isMap(fieldInformation.data)) {
                    fieldInformation.data = serializeMap(fieldInformation.data);
                }
                else if (!typeChecker.isElementDataPrimitive(fieldInformation.data)) {
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

    private JSONArray serializeArray(Object fieldData) throws JsonSerializationException {
        JSONArray jsonArray = new JSONArray();

        int length = Array.getLength(fieldData);
        for (int i = 0; i < length; i++) {
            Object arrayItem = Array.get(fieldData, i);
            if (!typeChecker.isElementDataPrimitive(arrayItem)) {
                arrayItem = serializeHelper(arrayItem);
            }
            jsonArray.put(arrayItem);
        }

        return jsonArray;
    }

    private JSONArray serializeCollection(Object fieldData) throws JsonSerializationException {
        JSONArray jsonArray = new JSONArray();

        for (Object collectionItem : ((Collection<?>) fieldData)) {
            if (!typeChecker.isElementDataPrimitive(collectionItem)) {
                collectionItem = serializeHelper(collectionItem);
            }
            jsonArray.put(collectionItem);
        }

        return jsonArray;
    }

    private JSONObject serializeMap(Object fieldData) throws JSONException, JsonSerializationException {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, ?> mapItem : ((Map<String, ?>) fieldData).entrySet()) {
            String key = mapItem.getKey();
            Object data = mapItem.getValue();

            if (!typeChecker.isElementDataPrimitive(data)) {
                data = serializeHelper(data);
            }
            jsonObject.put(key, data);
        }

        return jsonObject;
    }

}
