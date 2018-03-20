package simon.jsonserializer;

import org.json.JSONException;
import org.json.JSONObject;

import simon.jsonserializer.dataobject.TestObject;
import simon.jsonserializer.dataobject.TestObjectInner;
import simon.jsonserializer.serializer.JsonSerializationException;
import simon.jsonserializer.serializer.JsonSerializer;

public class Main {

    public static void main(String[] args) throws JsonSerializationException, JSONException {
        TestObjectInner testObjectInner = new TestObjectInner(1234, "inner bar data");
        TestObject testObject = new TestObject("foo data", "bar data", "baz data", testObjectInner);

        JSONObject jsonObject = JsonSerializer.serialize(testObject);
        System.out.println(jsonObject.toString(2));
    }

}
