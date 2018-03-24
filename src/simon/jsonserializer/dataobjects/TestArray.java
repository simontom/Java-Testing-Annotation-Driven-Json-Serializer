package simon.jsonserializer.dataobjects;

import simon.jsonserializer.parser.JsonField;

public class TestArray {
    @JsonField
    private final Integer[] arrayIntegers;

    @JsonField
    private Base[] arrayBases;

    public TestArray(Integer[] arrayIntegers, Base[] arrayBases) {
        this.arrayIntegers = arrayIntegers;
        this.arrayBases = arrayBases;
    }
}
