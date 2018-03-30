package simon.jsonserializer.jsonparser;

import com.sun.istack.internal.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformation;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeHelper;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {

    private final FieldInformationExtractor extractor;
    private final TypeHelper typeHelper;

    public JsonSerializer(FieldInformationExtractor fieldInformationExtractor, TypeHelper typeHelper) {
        this.extractor = fieldInformationExtractor;
        this.typeHelper = typeHelper;
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
        catch (Exception e) {
            throw new JsonParserException("Unable to Serialize object", e);
        }
    }

    private void processField(FieldInformation fieldInformation)
            throws JsonParserException, JSONException {

        if (typeHelper.isTypeArray(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeArray(fieldInformation.data);
        }
        else if (typeHelper.isTypeArrayList(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeArrayList(fieldInformation.data);
        }
        else if (typeHelper.isTypeHashMap(fieldInformation.data.getClass())) {
            fieldInformation.data = serializeHashMap(fieldInformation.data);
        }
        else if (!typeHelper.isDataPrimitive(fieldInformation.data) &&
                !typeHelper.isDataPrimitiveJson(fieldInformation.data)) {
            fieldInformation.data = serializeHelper(fieldInformation.data);
        }
    }

    private JSONArray serializeArray(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        for (Object item : ((Object[]) fieldData)) {
            if (!typeHelper.isDataPrimitive(item) &&
                    !typeHelper.isDataPrimitiveJson(item)) {
                item = serializeHelper(item);
            }
            jsonArray.put(item);
        }

        return jsonArray;
    }

    private JSONArray serializeArrayList(Object fieldData) throws JsonParserException {
        JSONArray jsonArray = new JSONArray();

        for (Object item : ((List<?>) fieldData)) {
            if (!typeHelper.isDataPrimitive(item) &&
                    !typeHelper.isDataPrimitiveJson(item)) {
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

            if (!typeHelper.isDataPrimitive(data) &&
                    !typeHelper.isDataPrimitiveJson(data)) {
                data = serializeHelper(data);
            }
            jsonObject.put(key, data);
        }

        return jsonObject;
    }

}
