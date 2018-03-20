package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import org.json.JSONObject;

import java.lang.reflect.Field;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {

    public static JSONObject serialize(@NotNull Object toBeSerialized) throws JsonSerializationException {
        requireNonNull(toBeSerialized);
        return serializeHelper(toBeSerialized);
    }

    private static JSONObject serializeHelper(Object toBeSerialized) throws JsonSerializationException {
        if (toBeSerialized == null) {
            return null;
        }

        try {
            Class<?> objectClass = toBeSerialized.getClass();
            JSONObject jsonObject = new JSONObject();

            for (Field field : objectClass.getDeclaredFields()) {
                String jsonElementKey = getJsonElementKey(field);
                if (jsonElementKey != null) {
                    TypeConverter typeConverter = getTypeConverter(field);
                    field.setAccessible(true);

                    Object jsonElementData = field.get(toBeSerialized);
                    if ((jsonElementData != null) && (typeConverter != null)) {
                        jsonElementData = typeConverter.convert(jsonElementData);
                    }
                    if ((jsonElementData != null) && !isElementDataPrimitive(jsonElementData.getClass())) {
                        jsonElementData = serializeHelper(jsonElementData);
                    }

                    jsonObject.put(jsonElementKey, jsonElementData);
                }
            }

            return jsonObject;
        }
        catch (Exception e) {
            throw new JsonSerializationException(e.getMessage(), e);
        }
    }

    private static boolean isElementDataPrimitive(Class<?> fieldDataClass) {
        return (fieldDataClass.isPrimitive() ||
                fieldDataClass.isAssignableFrom(String.class) ||
                fieldDataClass.isAssignableFrom(Boolean.class) ||
                fieldDataClass.isAssignableFrom(Character.class) ||
                Number.class.isAssignableFrom(fieldDataClass)
        );
    }

    private static String getJsonElementKey(Field field) {
        JsonField jsonPropertyKeyAnnotation = field.getAnnotation(JsonField.class);

        if (jsonPropertyKeyAnnotation == null) {
            return null;
        }

        String jsonPropertyKey = jsonPropertyKeyAnnotation.name();
        if (jsonPropertyKey.isEmpty()) {
            jsonPropertyKey = field.getName();
        }
        return jsonPropertyKey;
    }

    private static TypeConverter getTypeConverter(Field field) {
        JsonFieldConverter jsonTypeConverterAnnotation = field.getAnnotation(JsonFieldConverter.class);

        if (jsonTypeConverterAnnotation == null) {
            return null;
        }

        try {
            return jsonTypeConverterAnnotation.converterClass().newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
