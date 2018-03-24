package simon.jsonserializer.parser.helpers;

import simon.jsonserializer.parser.TypeConverter;

public class FieldInformation {
    public String name;
    public Object data;
    public boolean isOptional = true;
    public TypeConverter typeConverter;

    public FieldInformation(String name, Object data, boolean isOptional, TypeConverter typeConverter) {
        this.name = name;
        this.data = data;
        this.isOptional = isOptional;
        this.typeConverter = typeConverter;
    }
}
