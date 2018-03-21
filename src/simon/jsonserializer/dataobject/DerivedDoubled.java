package simon.jsonserializer.dataobject;

import simon.jsonserializer.serializer.JsonField;

public class DerivedDoubled extends Derived {
    @JsonField (name = "derived_doubled_xx")
    private String xx;

    @JsonField
    boolean dd_yy = true;

    public DerivedDoubled(Integer integerAsObject, int number, String bar, boolean dd_yy) {
        super(integerAsObject, number, bar, dd_yy);
        this.xx = bar;
        this.dd_yy = dd_yy;
    }
}
