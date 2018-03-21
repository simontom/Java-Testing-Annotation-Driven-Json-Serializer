package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.JsonField;

public class Derived extends Base {
    @JsonField (name = "derived_bar")
    private String bar;

    @JsonField
    boolean derivedBool = true;

    public Derived(Integer integerAsObject, int number, String bar, boolean derivedBool) {
        super(integerAsObject, number);
        this.bar = bar;
        this.derivedBool = derivedBool;
    }
}
