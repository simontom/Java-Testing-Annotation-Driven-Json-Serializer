package simon.jsonserializer.dataobjects;

import simon.jsonserializer.IntegerStringTypeConverter;
import simon.jsonserializer.jsonparser.JsonField;

public class Base {
    @JsonField (optional = false, name = "_mandatory_and_converted_", typeConverter = IntegerStringTypeConverter.class)
    private Integer integerAsObject;

    @JsonField (name = "test_number")
    private int number = 42;

    public Base() {
    }

    public Base(Integer integerAsObject, int number) {
        this.integerAsObject = integerAsObject;
        this.number = number;
    }

}
