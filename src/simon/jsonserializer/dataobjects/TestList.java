package simon.jsonserializer.dataobjects;

import java.util.List;

import simon.jsonserializer.parser.JsonField;

public class TestList {
    @JsonField
    private List<Integer> listIntegers;

    @JsonField
    private List<Base> listBases;

    public TestList() {
    }

    public TestList(List<Integer> listIntegers, List<Base> listBases) {
        this.listIntegers = listIntegers;
        this.listBases = listBases;
    }
}
