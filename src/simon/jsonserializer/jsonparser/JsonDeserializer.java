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
                Object data = toBeDeJsonified.opt(fieldInformation.name);
                if (data == null) {
                    if (fieldInformation.isOptional) {
                        continue;
                    }
                    String message = String.format("Mandatory Json Field '%s' is NULL", fieldInformation.name);
                    throw new JsonParserException(message);
                }

                data = fieldInformation.typeConverter.onDeserialization(data);
                processField(fieldInformation.field, instance, data);
            }

            return instance;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Deserialize object", e);
        }
    }

    private void processField(Field field, Object instance, Object data)
            throws IllegalAccessException, JsonParserException {

        if (typeChecker.isTypeString(data.getClass())) {
            processText(field, instance, (String) data);
        }
        else if (typeChecker.isTypeBoolean(data.getClass())) {
            field.set(instance, data);
        }
        else if (typeChecker.isTypeNumber(data.getClass())) {
            processNumber(field, instance, (Number) data);
        }
    }

    private void processText(Field field, Object instance, String text)
            throws IllegalAccessException, JsonParserException {

        Class<?> fieldClass = field.getType();
        if (typeChecker.isTypeString(fieldClass)) {
            field.set(instance, text);
        }
        else if (typeChecker.isTypeCharacter(fieldClass)) {
            if (text.length() != 1) {
                throw new JsonParserException("Cannot be casted to Character");
            }
            field.set(instance, text.charAt(0));
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
