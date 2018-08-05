package cz.simon.tests.dataobjects;

import cz.simon.jsonserializer.jsonparser.JsonField;

public class Derived extends Base {
    @JsonField (name = "derived_bar")
    private String bar;

    @JsonField
    private boolean derivedBool = true;

    public Derived() {
    }

    public Derived(Integer integerAsObject, int number, String bar, boolean derivedBool) {
        super(integerAsObject, number);
        this.bar = bar;
        this.derivedBool = derivedBool;
    }
}
