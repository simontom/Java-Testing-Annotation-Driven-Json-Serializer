package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.lang.reflect.Field;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformation;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeHelper;

import static java.util.Objects.requireNonNull;

public class JsonDeserializer {
    private final FieldInformationExtractor extractor;
    private final TypeHelper typeHelper;

    public JsonDeserializer(FieldInformationExtractor fieldInformationExtractor, TypeHelper typeHelper) {
        this.extractor = fieldInformationExtractor;
        this.typeHelper = typeHelper;
    }

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        try {
            T instance = clazz.newInstance();
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
            throws IllegalAccessException, JsonParserException {

        if (typeHelper.isTypeString(dataFromJson.getClass())) {
            processText(field, instance, (String) dataFromJson);
        }
        else if (typeHelper.isTypeBoolean(dataFromJson.getClass())) {
            field.set(instance, dataFromJson);
        }
        else if (typeHelper.isTypeNumber(dataFromJson.getClass())) {
            processNumber(field, instance, (Number) dataFromJson);
        }
    }

    private void processText(Field field, Object instance, String dataFromJson)
            throws IllegalAccessException, JsonParserException {

        Class<?> fieldClass = field.getType();
        if (typeHelper.isTypeString(fieldClass)) {
            field.set(instance, dataFromJson);
        }
        else if (typeHelper.isTypeCharacter(fieldClass)) {
            if (dataFromJson.length() != 1) {
                throw new JsonParserException("Cannot be casted to Character");
            }
            field.set(instance, dataFromJson.charAt(0));
        }
    }

    private void processNumber(Field field, Object instance, Number dataFromJson)
            throws IllegalAccessException {

        Class<?> fieldClass = field.getType();

        if (typeHelper.isTypeByte(fieldClass)) {
            field.set(instance, dataFromJson.byteValue());
        }
        else if (typeHelper.isTypeShort(fieldClass)) {
            field.set(instance, dataFromJson.shortValue());
        }
        else if (typeHelper.isTypeInteger(fieldClass)) {
            field.set(instance, dataFromJson.intValue());
        }
        else if (typeHelper.isTypeLong(fieldClass)) {
            field.set(instance, dataFromJson.longValue());
        }
        else if (typeHelper.isTypeFloat(fieldClass)) {
            field.set(instance, dataFromJson.floatValue());
        }
        else if (typeHelper.isTypeDouble(fieldClass)) {
            field.set(instance, dataFromJson.doubleValue());
        }
    }

}
