package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.JsonField;

public class TestObjectInner {
    @JsonField(name = "inner_foo")
    private final long foo;

    @JsonField(name = "inner_bar")
    private String bar;

    @JsonField
    boolean bool = true;
    @JsonField
    Boolean boolObject = true;

    public TestObjectInner(long foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "TestObjectInner{" +
                "foo=" + foo +
                ", bar='" + bar + '\'' +
                '}';
    }
}
