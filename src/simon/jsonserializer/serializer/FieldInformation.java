package simon.jsonserializer.serializer;

class FieldInformation {
    String name;
    TypeConverter typeConverter;
    Object data;

    FieldInformation(String name, Object data, TypeConverter typeConverter) {
        this.name = name;
        this.typeConverter = typeConverter;
        this.data = data;
    }
}
