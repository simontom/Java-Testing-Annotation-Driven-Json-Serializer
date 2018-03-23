package simon.jsonserializer;

import simon.jsonserializer.serializer.TypeConverter;

public class IntegerToStringTypeConverter implements TypeConverter<String, Integer> {
    @Override
    public String convertSerialization(Integer object) {
        return String.valueOf(object);
    }

    @Override
    public Integer convertDeserialization(String object) {
        try {
            return Integer.parseInt(object);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
