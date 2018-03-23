package simon.jsonserializer.dataobjects;

import java.util.Map;

import simon.jsonserializer.serializer.JsonField;

public class TestMap {
    @JsonField
    private final Map<String, Integer> mapIntegers;

    @JsonField
    private Map<String, Base> mapBases;

    public TestMap(Map<String, Integer> mapIntegers, Map<String, Base> mapBases) {
        this.mapIntegers = mapIntegers;
        this.mapBases = mapBases;
    }
}
