package simon.tests.dataobjects;

import java.util.Objects;

import simon.jsonserializer.jsonparser.JsonField;

public class TestCharString {
    @JsonField (optional = false, name = "mandatory")
    private String string;

    @JsonField
    private char char_primitive;

    @JsonField
    private Character char_object;

    public TestCharString() {
    }

    public TestCharString(String string, char char_primitive, Character char_object) {
        this.string = string;
        this.char_primitive = char_primitive;
        this.char_object = char_object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestCharString)) {
            return false;
        }
        TestCharString that = (TestCharString) o;
        return char_primitive == that.char_primitive &&
                Objects.equals(string, that.string) &&
                Objects.equals(char_object, that.char_object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, char_primitive, char_object);
    }
}
