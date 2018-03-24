package simon.jsonserializer.dataobjects;

import simon.jsonserializer.IntegerToStringTypeConverter;
import simon.jsonserializer.parser.JsonField;

public class Base {
    @JsonField (optional = false, name = "_mandatory_and_converted_", typeConverter = IntegerToStringTypeConverter.class)
    private final Integer integerAsObject;

    @JsonField (name = "test_number")
    private int number = 42;

    public Base(Integer integerAsObject, int number) {
        this.integerAsObject = integerAsObject;
        this.number = number;
    }

}
