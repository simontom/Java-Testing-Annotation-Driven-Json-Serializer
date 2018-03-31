package simon.tests.dataobjects;

import java.util.ArrayList;
import java.util.Objects;

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

    public ArrayList<Integer> getListIntegers() {
        return listIntegers;
    }

    public ArrayList<Base> getListBases() {
        return listBases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestList)) {
            return false;
        }
        TestList testList = (TestList) o;
        return Objects.equals(getListIntegers(), testList.getListIntegers()) &&
                Objects.equals(getListBases(), testList.getListBases());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListIntegers(), getListBases());
    }
}
