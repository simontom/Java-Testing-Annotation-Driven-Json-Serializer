package simon.jsonserializer;

import simon.jsonserializer.jsonparser.TypeConverter;

public class IntegerStringTypeConverter implements TypeConverter<String, Integer> {
    @Override
    public String onSerialization(Integer object) {
        return String.valueOf(object);
    }

    @Override
    public Integer onDeserialization(String object) {
        try {
            return Integer.parseInt(object);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }
}
