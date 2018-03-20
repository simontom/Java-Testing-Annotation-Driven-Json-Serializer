package simon.jsonserializer.serializer;

public class IntegerToStringTypeConverter implements TypeConverter<String> {
    @Override
    public String convert(Object object) {
        return String.valueOf(object);
    }
}
