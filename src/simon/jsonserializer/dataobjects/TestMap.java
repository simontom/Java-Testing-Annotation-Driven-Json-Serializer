package simon.jsonserializer.dataobjects;

import java.util.HashMap;

import simon.jsonserializer.jsonparser.JsonField;

public class TestMap {
    @JsonField
    private HashMap<String, Integer> mapIntegers;

    @JsonField (optional = false)
    private HashMap<String, Base> mapBases;

    public TestMap() {
    }

    public TestMap(HashMap<String, Integer> mapIntegers, HashMap<String, Base> mapBases) {
        this.mapIntegers = mapIntegers;
        this.mapBases = mapBases;
    }
}
