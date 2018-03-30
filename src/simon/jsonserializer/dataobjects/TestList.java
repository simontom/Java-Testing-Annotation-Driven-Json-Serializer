package simon.jsonserializer.dataobjects;

import java.util.ArrayList;

import simon.jsonserializer.jsonparser.JsonField;

public class TestList {
    @JsonField
    private ArrayList<Integer> listIntegers;

    @JsonField
    private ArrayList<Base> listBases;

    public TestList() {
    }

    public TestList(ArrayList<Integer> listIntegers, ArrayList<Base> listBases) {
        this.listIntegers = listIntegers;
        this.listBases = listBases;
    }
}
