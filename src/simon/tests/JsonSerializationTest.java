package simon.tests;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import simon.jsonserializer.dataobject.Base;
import simon.jsonserializer.dataobject.Derived;
import simon.jsonserializer.dataobject.DerivedDoubled;
import simon.jsonserializer.dataobject.TestClass;
import simon.jsonserializer.dataobject.TestInner;
import simon.jsonserializer.serializer.JsonSerializableFieldInformationExtractor;
import simon.jsonserializer.serializer.JsonSerializationException;
import simon.jsonserializer.serializer.JsonSerializer;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class JsonSerializationTest {

    JsonSerializer jsonSerializer;

    @Before
    public void setUp() {
        JsonSerializableFieldInformationExtractor extractor = new JsonSerializableFieldInformationExtractor();
        jsonSerializer = new JsonSerializer(extractor);
    }

    @Test
    public void a_serialize_WithInnerClass() throws JsonSerializationException, JSONException {
        TestInner inner = new TestInner(1234, "inner bar data");
        TestClass withInner = new TestClass("foo data", "bar data", "baz data", inner);

        serializeAndPrint("With Inner", withInner);
    }

    @Test
    public void b_serialize_BaseClass() throws JsonSerializationException, JSONException {
        Base base = new Base(42, 24);

        serializeAndPrint("Base", base);
    }

    @Test
    public void c_serialize_DerivedClass() throws JsonSerializationException, JSONException {
        Derived derived = new Derived(11, 22, "bar data", true);

        serializeAndPrint("Derived", derived);
    }

    @Test
    public void d_serialize_DerivedDoubledClass() throws JsonSerializationException, JSONException {
        DerivedDoubled derivedDoubled = new DerivedDoubled(11, 22, "bar data", true);

        serializeAndPrint("Derived Doubled", derivedDoubled);
    }

    private void serializeAndPrint(String testName, Object object) throws JsonSerializationException, JSONException {
        System.out.println("\t" + testName);
        JSONObject jsonObject = jsonSerializer.serialize(object);
        System.out.println(jsonObject.toString(4));
    }

}