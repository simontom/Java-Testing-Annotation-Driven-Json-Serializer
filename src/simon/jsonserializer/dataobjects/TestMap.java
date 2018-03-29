package simon.jsonserializer.dataobjects;

import java.util.Map;

import simon.jsonserializer.jsonparser.JsonField;

public class TestMap {
    @JsonField
    private Map<String, Integer> mapIntegers;

    @JsonField (optional = false)
    private Map<String, Base> mapBases;

    public TestMap() {
    }

    public TestMap(Map<String, Integer> mapIntegers, Map<String, Base> mapBases) {
        this.mapIntegers = mapIntegers;
        this.mapBases = mapBases;
    }
}
