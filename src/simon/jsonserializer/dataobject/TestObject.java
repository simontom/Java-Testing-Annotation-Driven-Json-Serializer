package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.IntegerToStringTypeConverter;
import simon.jsonserializer.serializer.JsonField;
import simon.jsonserializer.serializer.JsonFieldConverter;

public class TestObject {
    private final String foo;

    @JsonField
    private final String bar;
    @JsonField (name = "test_name")
    private final String baz;
    @JsonField (name = "test_number")
    private int number = 42;

    //region Number
    @JsonField(name = "__converted__")
    @JsonFieldConverter (converterClass = IntegerToStringTypeConverter.class)
    private Integer integerAsObject = 123;
    @JsonField
    private Long longAsObject = 1234L;
    @JsonField
    private Float floatAsObject = 12345.12345F;
    @JsonField
    private Double doubleAsObject = 123456.123456;
    //endregion Number

    //region Inner Object
    @JsonField (name = "inner")
    private TestObjectInner testObjectInner;
    @JsonField (name = "it_is_not_null")
    TestObjectInner testObjectInnerNotNull = new TestObjectInner(1, "2");
    @JsonField (name = "it_is_null")
    TestObjectInner testObjectInnerNull;
    //endregion Inner Object

    public TestObject(String foo, String bar, String baz, TestObjectInner testObjectInner) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
        this.testObjectInner = testObjectInner;
    }
}
