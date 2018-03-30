package simon.jsonserializer.jsonparser;

public interface
TypeConverter<TJson, TObject> {
    TJson onSerialization(TObject object);

    TObject onDeserialization(TJson object);


    class IDENTITY implements TypeConverter<Object, Object> {
        @Override
        public Object onSerialization(Object object) {
            return object;
        }

        @Override
        public Object onDeserialization(Object object) {
            return object;
        }
    }
}
