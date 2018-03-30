package simon.jsonserializer.jsonparser.helpers;

import java.lang.reflect.Field;

import simon.jsonserializer.jsonparser.TypeConverter;

public class FieldInformation {
    public final Field field;
    public final String name;
    public Object data;
    public final boolean isOptional;
    public final TypeConverter typeConverter;

    public FieldInformation(Field field, String name, Object data, boolean isOptional, TypeConverter typeConverter) {
        this.field = field;
        this.name = name;
        this.data = data;
        this.isOptional = isOptional;
        this.typeConverter = typeConverter;
    }
}
