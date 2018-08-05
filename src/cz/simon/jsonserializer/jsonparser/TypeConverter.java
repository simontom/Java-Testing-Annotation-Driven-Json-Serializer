package cz.simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;

public interface
TypeConverter<TJson, TObject> {
    TJson onSerialization(@NotNull TObject object);
    TObject onDeserialization(@NotNull TJson object);

    class IDENTITY implements TypeConverter<Object, Object> {
        @Override
        public Object onSerialization(@NotNull Object object) {
            return object;
        }
        @Override
        public Object onDeserialization(@NotNull Object object) {
            return object;
        }
    }
}
