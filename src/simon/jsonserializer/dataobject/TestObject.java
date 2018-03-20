package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.JsonField;

public class TestObject {
    private final String foo;

    @JsonField
    private final String bar;
    @JsonField(name = "test_name")
    private final String baz;
    @JsonField(name = "test_number")
    private int number = 42;

    @JsonField
    private Integer integerAsObject = 123;
    @JsonField
    private Long longAsObject = 1234L;
    @JsonField
    private Float floatAsObject = 12345.12345F;
    @JsonField
    private Double doubleAsObject = 123456.123456;

    @JsonField(name = "inner")
    private TestObjectInner testObjectInner;
    @JsonField(name = "it_is_not_null")
    TestObjectInner testObjectInnerNotNull = new TestObjectInner(1, "2");
    @JsonField(name = "it_is_null")
    TestObjectInner testObjectInnerNull;

    public TestObject(String foo, String bar, String baz, TestObjectInner testObjectInner) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
        this.testObjectInner = testObjectInner;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "foo='" + foo + '\'' +
                ", bar='" + bar + '\'' +
                ", baz='" + baz + '\'' +
                ", number=" + number +
                ", testObjectInner=" + testObjectInner.toString() +
                '}';
    }
}
