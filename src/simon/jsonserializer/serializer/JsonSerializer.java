package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import org.json.JSONObject;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {

    private final JsonSerializableFieldInformationExtractor fieldInformationExtractor;

    public JsonSerializer(JsonSerializableFieldInformationExtractor fieldInformationExtractor) {
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
        return (fieldDataClass.isPrimitive() ||
                fieldDataClass.isAssignableFrom(String.class) ||
                fieldDataClass.isAssignableFrom(Boolean.class) ||
                fieldDataClass.isAssignableFrom(Character.class) ||
                Number.class.isAssignableFrom(fieldDataClass)
        );
    }

    private void tryApplyTypeConverter(FieldInformation fieldInformation) {
        if ((fieldInformation.data != null) && (fieldInformation.typeConverter != null)) {
            fieldInformation.data = fieldInformation.typeConverter.convertSerialization(fieldInformation.data);
        }
    }

}
