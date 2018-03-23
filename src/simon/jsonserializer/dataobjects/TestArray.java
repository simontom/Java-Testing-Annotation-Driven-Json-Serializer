package simon.jsonserializer.dataobjects;

import simon.jsonserializer.serializer.JsonField;

public class TestArray {
    @JsonField
    private final Integer[] integers;

    @JsonField
    private Base[] bases;

    public TestArray(Integer[] integers, Base[] bases) {
        this.integers = integers;
        this.bases = bases;
    }
}
