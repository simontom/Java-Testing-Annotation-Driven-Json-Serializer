package simon.jsonserializer.dataobjects;

import simon.jsonserializer.IntegerToStringTypeConverter;
import simon.jsonserializer.serializer.JsonField;

public class Base {
    @JsonField (name = "__converted_int_to_string__", typeConverter = IntegerToStringTypeConverter.class)
    private final Integer integerAsObject;

    @JsonField (name = "test_number")
    private int number = 42;

    public Base(Integer integerAsObject, int number) {
        this.integerAsObject = integerAsObject;
        this.number = number;
    }
}
