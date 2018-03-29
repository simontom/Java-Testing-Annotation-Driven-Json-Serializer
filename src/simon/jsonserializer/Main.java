package simon.jsonserializer;

import org.json.JSONException;
import org.json.JSONObject;

import simon.jsonserializer.dataobjects.Base;
import simon.jsonserializer.dataobjects.Derived;
import simon.jsonserializer.dataobjects.DerivedDoubled;
import simon.jsonserializer.dataobjects.TestClass;
import simon.jsonserializer.dataobjects.TestInner;
import simon.jsonserializer.jsonparser.JsonSerializer;
import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;

public class Main {

    public static void main(String[] args) throws JsonParserException, JSONException {
        //region TestClass Data
        TestInner inner = new TestInner(1234, "inner bar data");
        TestClass withInner = new TestClass("foo data", "bar data", "baz data", inner);

        Base base = new Base(42, 24);
        Derived derived = new Derived(11, 22, "bar data", true);
        DerivedDoubled derivedDoubled = new DerivedDoubled(11, 22, "bar data", true);
        //endregion TestClass Data

        //region Serializer
        FieldInformationExtractor fieldInformationExtractor = new FieldInformationExtractor();
        TypeChecker typeChecker = new TypeChecker();
        JsonSerializer jsonSerializer = new JsonSerializer(fieldInformationExtractor, typeChecker);
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
