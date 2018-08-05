package cz.simon.jsonserializer.jsonparser.helpers;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.simon.jsonserializer.jsonparser.JsonField;
import cz.simon.jsonserializer.jsonparser.TypeConverter;
import cz.simon.jsonserializer.jsonparser.exceptions.FieldInformationExtractionException;

public class FieldInformationExtractor {

    public List<FieldInformation> extractFieldInformations(@NotNull Object object)
            throws FieldInformationExtractionException {

        List<FieldInformation> fieldsInformation = new ArrayList<>();

        try {
            for (Field field : getFieldsIncludingSuperclass(object)) {
                FieldInformation fieldInformation = extractFieldInformation(field, object);
                if (fieldInformation != null) {
                    fieldsInformation.add(fieldInformation);
                }
            }
        }
        catch (Exception e) {
            String message = "Unable to extract all field information for: " + object;
            throw new FieldInformationExtractionException(message, e);
        }

        return fieldsInformation;
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

    private FieldInformation extractFieldInformation(@NotNull Field field, Object object)
            throws IllegalAccessException, InstantiationException {

        JsonField jsonFieldAnnotation = field.getAnnotation(JsonField.class);

        if (jsonFieldAnnotation == null) { // Not going to be serialized
            return null;
        }

        // Get Element Key
        String informationKey = jsonFieldAnnotation.name();
        if (informationKey.isEmpty()) {
            informationKey = field.getName();
        }

        // Get Element Data
        field.setAccessible(true);
        Object informationData = field.get(object);

        // Get Is Element Optional
        boolean isOptional = jsonFieldAnnotation.optional();

        // Instantiate TypeConverter
        Class<? extends TypeConverter> typeConverterClass = jsonFieldAnnotation.typeConverter();
        TypeConverter typeConverter = typeConverterClass.newInstance();

        return new FieldInformation(field, informationKey, informationData, isOptional, typeConverter);
    }

}
