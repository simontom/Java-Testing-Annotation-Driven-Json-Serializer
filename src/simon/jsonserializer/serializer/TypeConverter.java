package simon.jsonserializer.serializer;

public interface TypeConverter<R> {
    R convert(Object object);
}
