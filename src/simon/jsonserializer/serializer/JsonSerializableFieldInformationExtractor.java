package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonSerializableFieldInformationExtractor {

    public List<FieldInformation> getSerializableFields(Object object) throws JsonSerializationException {
        List<FieldInformation> fieldsInformation = new ArrayList<>();

        if (object == null) {
            return fieldsInformation;
        }

        try {
            for (Field field : getFieldsIncludingSuperclass(object)) {
                String key = getElementKey(field);
                if (key != null) {
                    TypeConverter typeConverter = getTypeConverter(field);
                    Object data = getElementData(object, field);

                    FieldInformation fieldInfo = new FieldInformation(key, data, typeConverter);
                    fieldsInformation.add(fieldInfo);
                }
            }

            return fieldsInformation;
        }
        catch (IllegalAccessException e) {
            throw new JsonSerializationException(e.getMessage(), e);
        }
    }

    private List<Field> getFieldsIncludingSuperclass(Object object) {
        List<Field> allFields = new ArrayList<>();

        Class<?> currentClass = object.getClass();
        while (currentClass != Object.class) {
            Collections.addAll(allFields, currentClass.getDeclaredFields());
            currentClass = currentClass.getSuperclass();
        }

        return allFields;
    }

    private String getElementKey(Field field) {
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

    private TypeConverter getTypeConverter(Field field) {
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

    private Object getElementData(Object object, @NotNull Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }

}
