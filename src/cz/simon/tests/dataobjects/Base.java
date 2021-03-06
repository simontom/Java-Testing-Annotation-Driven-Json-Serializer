package cz.simon.tests.dataobjects;

import java.util.Objects;

import cz.simon.tests.typeconverters.IntegerStringTypeConverter;
import cz.simon.jsonserializer.jsonparser.JsonField;

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

    Integer getIntegerAsObject() {
        return integerAsObject;
    }

    int getNumber() {
        return number;
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
        return getNumber() == base.getNumber() &&
                Objects.equals(getIntegerAsObject(), base.getIntegerAsObject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntegerAsObject(), getNumber());
    }
}
