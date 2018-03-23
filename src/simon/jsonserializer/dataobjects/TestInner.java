package simon.jsonserializer.dataobjects;

import simon.jsonserializer.serializer.JsonField;

public class TestInner {
    @JsonField(name = "inner_bar")
    private String bar;

    @JsonField(name = "inner_foo")
    private final long foo;

    @JsonField
    boolean bool = true;
    @JsonField
    Boolean boolObject = true;

    public TestInner(long foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }
}
