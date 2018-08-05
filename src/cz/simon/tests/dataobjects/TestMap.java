package cz.simon.tests.dataobjects;

import java.util.HashMap;
import java.util.Objects;

import cz.simon.jsonserializer.jsonparser.JsonField;

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

    HashMap<String, Integer> getMapIntegers() {
        return mapIntegers;
    }

    HashMap<String, Base> getMapBases() {
        return mapBases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestMap)) {
            return false;
        }
        TestMap testMap = (TestMap) o;
        return Objects.equals(getMapIntegers(), testMap.getMapIntegers()) &&
                Objects.equals(getMapBases(), testMap.getMapBases());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMapIntegers(), getMapBases());
    }
}
