package simon.jsonserializer.serializer;

public interface TypeConverter<OUT, IN> {
    OUT convertSerialization(IN object);
    IN convertDeserialization(OUT object);


    class DEFAULT implements TypeConverter<Object, Object> {
        @Override
        public Object convertSerialization(Object object) {
            return object;
        }

        @Override
        public Object convertDeserialization(Object object) {
            return object;
        }
    }

}
