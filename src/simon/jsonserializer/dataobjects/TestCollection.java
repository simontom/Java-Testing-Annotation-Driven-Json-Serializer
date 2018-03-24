package simon.jsonserializer.dataobjects;

import java.util.List;

import simon.jsonserializer.parser.JsonField;

public class TestCollection {
    @JsonField
    private final List<Integer> listIntegers;

    @JsonField
    private List<Base> listBases;

    public TestCollection(List<Integer> listIntegers, List<Base> listBases) {
        this.listIntegers = listIntegers;
        this.listBases = listBases;
    }
}
