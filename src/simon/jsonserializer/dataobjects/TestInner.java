package simon.jsonserializer.dataobjects;

import simon.jsonserializer.parser.JsonField;

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
}
