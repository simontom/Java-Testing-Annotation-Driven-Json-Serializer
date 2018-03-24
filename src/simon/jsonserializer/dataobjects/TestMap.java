package simon.jsonserializer.dataobjects;

import java.util.Map;

import simon.jsonserializer.parser.JsonField;

public class TestMap {
    @JsonField
    private final Map<String, Integer> mapIntegers;

    @JsonField (optional = false)
    private Map<String, Base> mapBases;

    public TestMap(Map<String, Integer> mapIntegers, Map<String, Base> mapBases) {
        this.mapIntegers = mapIntegers;
        this.mapBases = mapBases;
    }
}
