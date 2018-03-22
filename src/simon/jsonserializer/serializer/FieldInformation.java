package simon.jsonserializer.serializer;

class FieldInformation {
    String name;
    Object data;
    boolean isOptional = true;
    TypeConverter typeConverter;

    FieldInformation(String name, Object data, boolean isOptional, TypeConverter typeConverter) {
        this.name = name;
        this.data = data;
        this.isOptional = isOptional;
        this.typeConverter = typeConverter;
    }
}
