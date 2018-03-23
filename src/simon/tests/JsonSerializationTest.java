package simon.tests;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import simon.jsonserializer.dataobjects.Base;
import simon.jsonserializer.dataobjects.Derived;
import simon.jsonserializer.dataobjects.DerivedDoubled;
import simon.jsonserializer.dataobjects.TestArray;
import simon.jsonserializer.dataobjects.TestClass;
import simon.jsonserializer.dataobjects.TestCollection;
import simon.jsonserializer.dataobjects.TestInner;
import simon.jsonserializer.dataobjects.TestMap;
import simon.jsonserializer.serializer.JsonFieldInformationExtractor;
import simon.jsonserializer.serializer.exceptions.JsonSerializationException;
import simon.jsonserializer.serializer.JsonSerializer;
import simon.jsonserializer.serializer.TypeChecker;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class JsonSerializationTest {

    JsonSerializer jsonSerializer;
    Random random;

    @Before
    public void setUp() {
        JsonFieldInformationExtractor extractor = new JsonFieldInformationExtractor();
        TypeChecker typeChecker = new TypeChecker();
        jsonSerializer = new JsonSerializer(extractor, typeChecker);

        random = new Random();
    }

    @Test
    public void a_serialize_WithInnerClass() throws JsonSerializationException, JSONException {
        TestInner inner = new TestInner(getRandomInt(), "inner bar data");
        TestClass withInner = new TestClass("foo data", "bar data", "baz data", inner);

        serializeAndPrint("With Inner", withInner);
    }

    @Test
    public void b_serialize_BaseClass() throws JsonSerializationException, JSONException {
        Base base = new Base(getRandomInt(), getRandomInt());

        serializeAndPrint("Base", base);
    }

    @Test
    public void c_serialize_DerivedClass() throws JsonSerializationException, JSONException {
        Derived derived = new Derived(getRandomInt(), getRandomInt(), "bar data", true);

        serializeAndPrint("Derived", derived);
    }

    @Test
    public void d_serialize_DerivedDoubledClass() throws JsonSerializationException, JSONException {
        DerivedDoubled derivedDoubled = new DerivedDoubled(getRandomInt(), getRandomInt(), "bar data", true);

        serializeAndPrint("Derived Doubled", derivedDoubled);
    }

    @Test
    public void e_serialize_ClassWithArray() throws JsonSerializationException, JSONException {
        Integer[] integers = {getRandomInt(), getRandomInt(), getRandomInt()};
        Base[] bases = {new Base(getRandomInt(), getRandomInt()), new Base(getRandomInt(), getRandomInt())};
        TestArray testArray = new TestArray(integers, bases);

        serializeAndPrint("Class With Array", testArray);
    }

    @Test
    public void f_serialize_ClassWithCollection() throws JsonSerializationException, JSONException {
        List<Integer> integers = Arrays.asList(getRandomInt(), getRandomInt(), getRandomInt());
        List<Base> bases = Arrays.asList(new Base(getRandomInt(), getRandomInt()), new Base(getRandomInt(), getRandomInt()));
        TestCollection testCollection = new TestCollection(integers, bases);

        serializeAndPrint("Class With Collection", testCollection);
    }

    @Test
    public void g_serialize_ClassWithMap() throws JsonSerializationException, JSONException {
        Map<String, Integer> mapIntegers = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};
        Map<String, Base> mapBases = new HashMap<String, Base>() {{
            put("base 1", new Base(getRandomInt(), getRandomInt()));
            put("base 2", new Base(getRandomInt(), getRandomInt()));
        }};
        TestMap testMap = new TestMap(mapIntegers, mapBases);

        serializeAndPrint("Class With Map", testMap);
    }


    private int getRandomInt() {
        return random.nextInt();
    }

    private void serializeAndPrint(String testName, Object object) throws JsonSerializationException, JSONException {
        System.out.println("\t" + testName);
        JSONObject jsonObject = jsonSerializer.serialize(object);
        System.out.println(jsonObject.toString(4));
    }

}