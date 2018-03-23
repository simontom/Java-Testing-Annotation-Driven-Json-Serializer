package simon.jsonserializer.dataobjects;

import java.util.List;

import simon.jsonserializer.serializer.JsonField;

public class TestCollection {
    @JsonField
    private final List<Integer> integers;

    @JsonField
    private List<Base> bases;

    public TestCollection(List<Integer> integers, List<Base> bases) {
        this.integers = integers;
        this.bases = bases;
    }
}
