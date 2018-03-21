package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.IntegerToStringTypeConverter;
import simon.jsonserializer.serializer.JsonField;
import simon.jsonserializer.serializer.JsonFieldConverter;

public class Base {
    @JsonField (name = "__converted_int_to_string__")
    @JsonFieldConverter (converterClass = IntegerToStringTypeConverter.class)
    private final Integer integerAsObject;

    @JsonField (name = "test_number")
    private int number = 42;

    public Base(Integer integerAsObject, int number) {
        this.integerAsObject = integerAsObject;
        this.number = number;
    }
}
