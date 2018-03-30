package simon.jsonserializer;

import org.jetbrains.annotations.NotNull;

import simon.jsonserializer.jsonparser.TypeConverter;

public class IntegerStringTypeConverter implements TypeConverter<String, Integer> {
    @Override
    public String onSerialization(@NotNull Integer object) {
        return String.valueOf(object);
    }

    @Override
    public Integer onDeserialization(@NotNull String object) {
        try {
            return Integer.parseInt(object);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }
}
