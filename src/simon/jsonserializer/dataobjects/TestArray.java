package simon.jsonserializer.dataobjects;

import simon.jsonserializer.parser.JsonField;

public class TestArray {
    @JsonField
    private Integer[] arrayIntegers;

    @JsonField
    private Base[] arrayBases;

    public TestArray() {
    }

    public TestArray(Integer[] arrayIntegers, Base[] arrayBases) {
        this.arrayIntegers = arrayIntegers;
        this.arrayBases = arrayBases;
    }
}
