package simon.jsonserializer.dataobjects;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Base)) {
            return false;
        }
        Base base = (Base) o;
        return number == base.number &&
                Objects.equals(integerAsObject, base.integerAsObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integerAsObject, number);
    }
}
