package simon.tests.dataobjects;

import java.util.Arrays;

import simon.jsonserializer.jsonparser.JsonField;

public class TestArray {
    @JsonField
    private Integer[] arrayIntegers;

    @JsonField
    private Base[] arrayBases;

    public TestArray() {
    }

    public TestArray(Integer[] arrayIntegers, Base[] arrayBases) {
        this.arrayIntegers = arrayIntegers;
        this.arrayBases = arrayBases;
    }

    public Integer[] getArrayIntegers() {
        return arrayIntegers;
    }

    public Base[] getArrayBases() {
        return arrayBases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestArray)) {
            return false;
        }
        TestArray testArray = (TestArray) o;
        return Arrays.equals(getArrayIntegers(), testArray.getArrayIntegers()) &&
                Arrays.equals(getArrayBases(), testArray.getArrayBases());
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getArrayIntegers());
        result = 31 * result + Arrays.hashCode(getArrayBases());
        return result;
    }
}
