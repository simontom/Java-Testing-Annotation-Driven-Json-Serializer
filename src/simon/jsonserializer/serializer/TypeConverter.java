package simon.jsonserializer.serializer;

public interface TypeConverter<OUT, IN> {

    // Used during serialization
    OUT convertSerialization(IN object);

    // Used during deserialization
    IN convertDeserialization(OUT object);

}
