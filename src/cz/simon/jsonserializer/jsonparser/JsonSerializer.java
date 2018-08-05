package cz.simon.jsonserializer.jsonparser;

import com.sun.istack.internal.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import cz.simon.jsonserializer.jsonparser.helpers.FieldInformation;
import cz.simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import cz.simon.jsonserializer.jsonparser.helpers.TypeChecker;

import static java.util.Objects.requireNonNull;

// -------------------------------------------
// TODO: Maybe using HashMap<Class, ISerializer> would be better than using TypeChecker
// -------------------------------------------

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
                    String message = String.format("Mandatory Field '%s' is NULL", fieldInformation.name);
                    throw new JsonParserException(message);
                }

                fieldInformation.data = fieldInformation.typeConverter.onSerialization(fieldInformation.data);
                processField(fieldInformation);
                jsonObject.put(fieldInformation.name, fieldInformation.data);
            }

            return jsonObject;
        }
        catch (JsonParserException e) {
            throw e;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Serialize object", e);
        }
    }

    private void processField(FieldInformation fieldInformation)
            throws JsonParserException, JSONException {

        // TODO: Rewrite to using Serializer interface and Map<Class, Serializer>
//        Map<Class, ISerializer> serializers;
//        Class fieldDataClass = fieldInformation.data.getClass();
//        fieldInformation.data = serializers.get(fieldDataClass).serialize(fieldInformation.data);

        if (typeChecker.isTypeArray(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeArray(fieldInformation.data);
        }
        else if (typeChecker.isTypeArrayList(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeArrayList(fieldInformation.data);
        }
        else if (typeChecker.isTypeHashMap(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeHashMap(fieldInformation.data);
        }
        else if (!typeChecker.isDataPrimitive(fieldInformation.data) &&
                !typeChecker.isTypePrimitiveJson(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeHelper(fieldInformation.data);
        }
    }

    private JSONArray serializeArray(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        for (Object item : ((Object[]) fieldData)) {
            if (!typeChecker.isDataPrimitive(item) &&
                    !typeChecker.isTypePrimitiveJson(item.getClass())) {
                item = serializeHelper(item);
            }
            jsonArray.put(item);
        }

        return jsonArray;
    }

    private JSONArray serializeArrayList(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        for (Object item : ((List<?>) fieldData)) {
            if (!typeChecker.isDataPrimitive(item) &&
                    !typeChecker.isTypePrimitiveJson(item.getClass())) {
                item = serializeHelper(item);
            }
            jsonArray.put(item);
        }

        return jsonArray;
    }

    private JSONObject serializeHashMap(Object fieldData) throws JSONException, JsonParserException {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, ?> item : ((HashMap<String, ?>) fieldData).entrySet()) {
            String key = item.getKey();
            Object data = item.getValue();

            if (!typeChecker.isDataPrimitive(data) &&
                    !typeChecker.isTypePrimitiveJson(data.getClass())) {
                data = serializeHelper(data);
            }
            jsonObject.put(key, data);
        }

        return jsonObject;
    }

}
