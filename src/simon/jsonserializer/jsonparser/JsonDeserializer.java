package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformation;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;
import simon.jsonserializer.jsonparser.helpers.TypeCreator;
import simon.jsonserializer.jsonparser.helpers.Utils;

import static java.util.Objects.requireNonNull;

public class JsonDeserializer {
    private final FieldInformationExtractor extractor;
    private final TypeChecker typeChecker;
    private final TypeCreator typeCreator;

    public JsonDeserializer(FieldInformationExtractor fieldInformationExtractor,
                            TypeChecker typeChecker, TypeCreator typeCreator) {

        this.extractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
        this.typeCreator = typeCreator;
    }

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        try {
            T instance = typeCreator.createInstanceFor(clazz);
            for (FieldInformation fieldInformation : extractor.extractFieldInformations(instance)) {
                Object dataFromJson = toBeDeJsonified.opt(fieldInformation.name);
                if (dataFromJson == null) {
                    if (fieldInformation.isOptional) {
                        continue;
                    }
                    String message = String.format("Mandatory Json Field '%s' is NULL", fieldInformation.name);
                    throw new JsonParserException(message);
                }

                dataFromJson = fieldInformation.typeConverter.onDeserialization(dataFromJson);
                processField(fieldInformation.field, instance, dataFromJson);
            }

            return instance;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Deserialize object", e);
        }
    }

    private void processField(Field field, Object instance, Object dataFromJson)
            throws IllegalAccessException, JsonParserException, JSONException {

        Class<?> dataFromJsonClass = dataFromJson.getClass();
        Class<?> fieldClass = field.getType();

        if (typeChecker.isTypeString(dataFromJsonClass)) {
            Object dataForField = createProcessedText(fieldClass, (String) dataFromJson);
            field.set(instance, dataForField);
        }
        else if (typeChecker.isTypeBoolean(dataFromJsonClass)) {
            field.set(instance, dataFromJson);
        }
        else if (typeChecker.isTypeNumber(dataFromJsonClass)) {
            Number dataForField = createProcessedNumber(fieldClass, (Number) dataFromJson);
            field.set(instance, dataForField);
        }
        else if (typeChecker.isTypeJSONObject(dataFromJsonClass)) {
            processJsonObject(field, instance, (JSONObject) dataFromJson);
        }
        else if (typeChecker.isTypeJSONArray(dataFromJsonClass)) {
            processJsonArray(field, instance, (JSONArray) dataFromJson);
        }
    }

    private Object createProcessedText(Class<?> fieldClass, String dataFromJson) throws JsonParserException {
        if (typeChecker.isTypeString(fieldClass)) {
            return dataFromJson;
        }
        else if (typeChecker.isTypeChar(fieldClass) && (dataFromJson.length() == 1)) {
            return dataFromJson.charAt(0);
        }

        throw new JsonParserException("Cannot be casted to Character");
    }

    private Number createProcessedNumber(Class<?> fieldClass, Number dataFromJson) throws JsonParserException {
        if (typeChecker.isTypeByte(fieldClass)) {
            return dataFromJson.byteValue();
        }
        else if (typeChecker.isTypeShort(fieldClass)) {
            return dataFromJson.shortValue();
        }
        else if (typeChecker.isTypeInteger(fieldClass)) {
            return dataFromJson.intValue();
        }
        else if (typeChecker.isTypeLong(fieldClass)) {
            return dataFromJson.longValue();
        }
        else if (typeChecker.isTypeFloat(fieldClass)) {
            return dataFromJson.floatValue();
        }
        else if (typeChecker.isTypeDouble(fieldClass)) {
            return dataFromJson.doubleValue();
        }

        throw new JsonParserException("Unknown Number type");
    }

    private void processJsonObject(Field field, Object instance, JSONObject jsonObject)
            throws JsonParserException, IllegalAccessException, JSONException {

        Class<?> fieldClass = field.getType();
        if (typeChecker.isTypeHashMap(fieldClass)) {
            Class<?> itemClass = typeChecker.getIterableItemClass(field);
            boolean isItemClassNumber = typeChecker.isTypeNumber(itemClass);
            boolean isItemClassStringChar = typeChecker.isTypeString(itemClass) || typeChecker.isTypeChar(itemClass);
            boolean isItemClassBoolean = typeChecker.isTypeBoolean(itemClass);
            boolean isItemClassJson = typeChecker.isTypePrimitiveJson(itemClass);

            HashMap hashMap = typeCreator.createHashMapOfType(itemClass, jsonObject.length());

            for (String keyJson : Utils.iteratorToIterable((Iterator<String>) jsonObject.keys())) {
                if (isItemClassNumber) {
                    Number number = (Number) jsonObject.get(keyJson);
                    hashMap.put(keyJson, createProcessedNumber(itemClass, number));
                }
                else if (isItemClassStringChar) {
                    String string = jsonObject.getString(keyJson);
                    hashMap.put(keyJson, createProcessedText(itemClass, string));
                }
                else if (isItemClassBoolean) {
                    hashMap.put(keyJson, jsonObject.getBoolean(keyJson));
                }
                else if (isItemClassJson) {
                    hashMap.put(keyJson, jsonObject.get(keyJson));
                }
                else {
                    hashMap.put(keyJson, deserializeHelper(jsonObject.getJSONObject(keyJson), itemClass));
                }
            }

            field.set(instance, hashMap);

            int karel = 1;
        }
        else if (!typeChecker.isTypePrimitive(fieldClass)) {
            Object deserialized = deserializeHelper(jsonObject, fieldClass);
            field.set(instance, deserialized);
        }
    }

    private void processJsonArray(Field field, Object instance, JSONArray jsonArray)
            throws JSONException, JsonParserException, IllegalAccessException {

        Class<?> fieldClass = field.getType();
        Class<?> itemClass = typeChecker.getIterableItemClass(field);

        boolean isItemClassNumber = typeChecker.isTypeNumber(itemClass);
        boolean isItemClassStringChar = typeChecker.isTypeString(itemClass) || typeChecker.isTypeChar(itemClass);
        boolean isItemClassBoolean = typeChecker.isTypeBoolean(itemClass);
        boolean isItemClassJson = typeChecker.isTypePrimitiveJson(itemClass);

        if (typeChecker.isTypeArray(fieldClass)) {
            Object[] array = typeCreator.createArrayOfType(itemClass, jsonArray.length());

            for (int index = 0; index < jsonArray.length(); index++) {
                if (isItemClassNumber) {
                    Number number = (Number) jsonArray.get(index);
                    array[index] = createProcessedNumber(itemClass, number);
                }
                else if (isItemClassStringChar) {
                    String string = jsonArray.getString(index);
                    array[index] = createProcessedText(itemClass, string);
                }
                else if (isItemClassBoolean) {
                    array[index] = jsonArray.getBoolean(index);
                }
                else if (isItemClassJson) {
                    array[index] = jsonArray.get(index);
                }
                else {
                    array[index] = deserializeHelper(jsonArray.getJSONObject(index), itemClass);
                }
            }

            field.set(instance, array);
        }
        else if (typeChecker.isTypeArrayList(fieldClass)) {
            ArrayList arrayList = typeCreator.createArrayListOfType(itemClass, jsonArray.length());

            for (int index = 0; index < jsonArray.length(); index++) {
                if (isItemClassNumber) {
                    Number number = (Number) jsonArray.get(index);
                    arrayList.add(createProcessedNumber(itemClass, number));
                }
                else if (isItemClassStringChar) {
                    String string = jsonArray.getString(index);
                    arrayList.add(createProcessedText(itemClass, string));
                }
                else if (isItemClassBoolean) {
                    arrayList.add(jsonArray.getBoolean(index));
                }
                else if (isItemClassJson) {
                    arrayList.add(jsonArray.get(index));
                }
                else {
                    arrayList.add(deserializeHelper(jsonArray.getJSONObject(index), itemClass));
                }
            }

            field.set(instance, arrayList);
        }
    }

}
