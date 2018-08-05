package cz.simon.tests.dataobjects;

import java.util.Objects;

import cz.simon.jsonserializer.jsonparser.JsonField;
import cz.simon.tests.typeconverters.IntegerStringTypeConverter;

public class TestWithInner {
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
    @JsonField (name = "__converted__", typeConverter = IntegerStringTypeConverter.class)
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
    TestInner testInnerNotNull = new TestInner(11, "22");
    @JsonField (name = "it_is_null")
    TestInner testInnerNull;
    //endregion Inner Object

    public TestWithInner() {
    }

    public TestWithInner(String foo, String bar, String baz, TestInner testInner) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
        this.testInner = testInner;
    }

    public TestInner getTestInner() {
        return testInner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestWithInner)) {
            return false;
        }
        TestWithInner that = (TestWithInner) o;
        return number == that.number &&
                Objects.equals(foo, that.foo) &&
                Objects.equals(bar, that.bar) &&
                Objects.equals(baz, that.baz) &&
                Objects.equals(integerAsObject, that.integerAsObject) &&
                Objects.equals(longAsObject, that.longAsObject) &&
                Objects.equals(floatAsObject, that.floatAsObject) &&
                Objects.equals(doubleAsObject, that.doubleAsObject) &&
                Objects.equals(getTestInner(), that.getTestInner()) &&
                Objects.equals(testInnerNotNull, that.testInnerNotNull) &&
                Objects.equals(testInnerNull, that.testInnerNull);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, bar, baz, number, integerAsObject, longAsObject, floatAsObject, doubleAsObject, getTestInner(), testInnerNotNull, testInnerNull);
    }
}
