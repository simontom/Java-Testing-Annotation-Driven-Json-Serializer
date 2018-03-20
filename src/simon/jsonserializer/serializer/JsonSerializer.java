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
                if (field.isAnnotationPresent(JsonField.class)) {
                    field.setAccessible(true);

                    String jsonElementKey = getPropertyKey(field);
                    Object jsonElementData;
                    if (isPrimitive(field)) {
                        jsonElementData = field.get(toBeSerialized);
                    }
                    else {
                        jsonElementData = serializeHelper(field.get(toBeSerialized));
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

    private static boolean isPrimitive(Field field) {
        return (field.getType().isPrimitive() ||
                field.getType().isAssignableFrom(String.class) ||
                field.getType().isAssignableFrom(Boolean.class) ||
                field.getType().isAssignableFrom(Character.class) ||
                Number.class.isAssignableFrom(field.getType())
        );
    }

    private static String getPropertyKey(Field field) {
        String jsonPropertyKey = field.getAnnotation(JsonField.class).name();

        if (jsonPropertyKey.isEmpty()) {
            return field.getName();
        }

        return jsonPropertyKey;
    }

}
