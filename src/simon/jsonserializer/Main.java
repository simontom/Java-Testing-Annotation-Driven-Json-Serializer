package simon.jsonserializer;

import org.json.JSONException;
import org.json.JSONObject;

import simon.jsonserializer.dataobject.Base;
import simon.jsonserializer.dataobject.Derived;
import simon.jsonserializer.dataobject.DerivedDoubled;
import simon.jsonserializer.dataobject.TestClass;
import simon.jsonserializer.dataobject.TestInner;
import simon.jsonserializer.serializer.JsonFieldInformationExtractor;
import simon.jsonserializer.serializer.JsonSerializationException;
import simon.jsonserializer.serializer.JsonSerializer;

public class Main {

    public static void main(String[] args) throws JsonSerializationException, JSONException {
        //region TestClass Data
        TestInner inner = new TestInner(1234, "inner bar data");
        TestClass withInner = new TestClass("foo data", "bar data", "baz data", inner);

        Base base = new Base(42, 24);
        Derived derived = new Derived(11, 22, "bar data", true);
        DerivedDoubled derivedDoubled = new DerivedDoubled(11, 22, "bar data", true);
        //endregion TestClass Data

        //region Serializer
        JsonFieldInformationExtractor fieldInformationExtractor = new JsonFieldInformationExtractor();
        JsonSerializer jsonSerializer = new JsonSerializer(fieldInformationExtractor);
        JSONObject jsonObject;
        //endregion Serializer

        //region Serialization TestClass
        System.out.println("Inner");
        jsonObject = jsonSerializer.serialize(inner);
        System.out.println(jsonObject.toString(2));

        System.out.println("\n\nWith Inner");
        jsonObject = jsonSerializer.serialize(withInner);
        System.out.println(jsonObject.toString(2));

        System.out.println("\n\nBase");
        jsonObject = jsonSerializer.serialize(base);
        System.out.println(jsonObject.toString(2));

        System.out.println("\n\nDerived");
        jsonObject = jsonSerializer.serialize(derived);
        System.out.println(jsonObject.toString(2));

        System.out.println("\n\nDerived Doubled");
        jsonObject = jsonSerializer.serialize(derivedDoubled);
        System.out.println(jsonObject.toString(2));
        //endregion Serialization TestClass
    }

}
