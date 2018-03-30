package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.lang.reflect.Field;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformation;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;

import static java.util.Objects.requireNonNull;

public class JsonDeserializer {
    private final FieldInformationExtractor extractor;
    private final TypeChecker typeChecker;

    public JsonDeserializer(FieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        this.extractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
    }

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        try {
            T instance = clazz.newInstance();
            for (FieldInformation fieldInformation : extractor.extractFieldInformations(instance)) {
                Object data;
                if (fieldInformation.isOptional) {
                    data = toBeDeJsonified.opt(fieldInformation.name);
                    if (data == null) {
                        continue;
                    }
                }
                else {
                    data = toBeDeJsonified.get(fieldInformation.name);
                }

                // TODO: Add deserialization logic
                processField(fieldInformation, instance, data);
            }

            return instance;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Deserialize object", e);
        }
    }

    private void processField(FieldInformation fieldInformation, Object instance, Object data)
            throws IllegalAccessException {

        int karel = 1;

        data = fieldInformation.typeConverter.onDeserialization(data);

        if (typeChecker.isTypeString(data.getClass())) {
            fieldInformation.field.set(instance, (String)data);
        }
        else if (typeChecker.isTypeNumber(data.getClass())) {
            processNumber(fieldInformation.field, instance, (Number) data);
        }


        if (typeChecker.isArray(fieldInformation.data)) {
//            fieldInformation.data = serializeArray(fieldInformation.data);
        }
        else if (typeChecker.isArrayList(fieldInformation.data)) {
//            fieldInformation.data = serializeArrayList(fieldInformation.data);
        }
        else if (typeChecker.isHashMap(fieldInformation.data)) {
//            fieldInformation.data = serializeHashMap(fieldInformation.data);
        }
        else if (!typeChecker.isDataPrimitive(fieldInformation.data) &&
                !typeChecker.isDataPrimitiveJson(fieldInformation.data)) {
//            fieldInformation.data = serializeHelper(fieldInformation.data);
        }
    }

    private void processNumber(Field field, Object instance, Number number)
            throws IllegalAccessException {

        Class<?> fieldClass = field.getType();

        if (typeChecker.isTypeByte(fieldClass)) {
            field.set(instance, number.byteValue());
        }
        else if (typeChecker.isTypeShort(fieldClass)) {
            field.set(instance, number.shortValue());
        }
        else if (typeChecker.isTypeInteger(fieldClass)) {
            field.set(instance, number.intValue());
        }
        else if (typeChecker.isTypeLong(fieldClass)) {
            field.set(instance, number.longValue());
        }
        else if (typeChecker.isTypeFloat(fieldClass)) {
            field.set(instance, number.floatValue());
        }
        else if (typeChecker.isTypeDouble(fieldClass)) {
            field.set(instance, number.doubleValue());
        }
    }

}
