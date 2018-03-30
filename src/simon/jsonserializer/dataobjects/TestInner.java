package simon.jsonserializer.dataobjects;

import java.util.Objects;

import simon.jsonserializer.jsonparser.JsonField;

public class TestInner {
    @JsonField (name = "inner_bar")
    private String bar;

    @JsonField (name = "inner_foo")
    private long foo;

    @JsonField
    boolean bool = true;
    @JsonField
    Boolean boolObject = true;

    public TestInner() {
    }

    public TestInner(long foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestInner)) {
            return false;
        }
        TestInner testInner = (TestInner) o;
        return foo == testInner.foo &&
                bool == testInner.bool &&
                Objects.equals(bar, testInner.bar) &&
                Objects.equals(boolObject, testInner.boolObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bar, foo, bool, boolObject);
    }
}
