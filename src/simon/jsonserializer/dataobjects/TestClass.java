package simon.jsonserializer.dataobjects;

import simon.jsonserializer.IntegerToStringTypeConverter;
import simon.jsonserializer.parser.JsonField;

public class TestClass {
    //region Not Annotated for serialization
    private String foo;
    //endregion Not Annotated for serialization

    //region String
    @JsonField
    private String bar;
    @JsonField (name = "test_name")
    private String baz;
    //endregion String

    //region Number
    @JsonField (name = "test_number")
    private int number = 42;
    @JsonField (name = "__converted__", typeConverter = IntegerToStringTypeConverter.class)
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
    private TestInner testInner;
    @JsonField (name = "it_is_not_null")
    TestInner testInnerNotNull = new TestInner(1, "2");
    @JsonField (name = "it_is_null")
    TestInner testInnerNull;
    //endregion Inner Object

    public TestClass() {
    }

    public TestClass(String foo, String bar, String baz, TestInner testInner) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
        this.testInner = testInner;
    }
}
