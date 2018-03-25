package simon.jsonserializer.parser;

import com.sun.istack.internal.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Map;

import simon.jsonserializer.parser.exceptions.JsonParserException;
import simon.jsonserializer.parser.helpers.FieldInformation;
import simon.jsonserializer.parser.helpers.FieldInformationExtractor;
import simon.jsonserializer.parser.helpers.TypeChecker;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {

    private final FieldInformationExtractor extractor;
    private final TypeChecker typeChecker;

    public JsonSerializer(FieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        this.extractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
    }

    public JSONObject serialize(@NotNull Object toBeJsonified) throws JsonParserException {
        requireNonNull(toBeJsonified);
        return serializeHelper(toBeJsonified);
    }

    private JSONObject serializeHelper(Object toBeJsonified) throws JsonParserException {
        if (toBeJsonified == null) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject();

            for (FieldInformation fieldInformation : extractor.extractFieldInformations(toBeJsonified)) {
                if (fieldInformation.data == null) {
                    if (fieldInformation.isOptional) {
                        continue;
                    }
                    String message = String.format("Mandatory Json Field '%s' is NULL", fieldInformation.name);
                    throw new IllegalArgumentException(message);
                }

                processFieldInformation(fieldInformation);
                jsonObject.put(fieldInformation.name, fieldInformation.data);
            }

            return jsonObject;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Serialize object", e);
        }
    }

    private void processFieldInformation(FieldInformation fieldInformation)
            throws JsonParserException, JSONException {

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
    }

    private JSONArray serializeArray(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        Object arrayItems[] = (Object[]) fieldData;
        for (Object arrayItem : arrayItems) {
            if (!typeChecker.isElementDataPrimitive(arrayItem)) {
                arrayItem = serializeHelper(arrayItem);
            }
            jsonArray.put(arrayItem);
        }

        return jsonArray;
    }

    private JSONArray serializeCollection(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        for (Object collectionItem : ((Collection<?>) fieldData)) {
            if (!typeChecker.isElementDataPrimitive(collectionItem)) {
                collectionItem = serializeHelper(collectionItem);
            }
            jsonArray.put(collectionItem);
        }

        return jsonArray;
    }

    private JSONObject serializeMap(Object fieldData) throws JSONException, JsonParserException {
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
