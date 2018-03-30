package simon.jsonserializer;

import org.json.JSONException;
import org.json.JSONObject;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;

public class Main {

    public static void main(String[] args) throws JsonParserException, JSONException {
        Character c = 'x';
        JSONObject jo = new JSONObject();
        jo.put("t", c);

        String joS = jo.toString();
        JSONObject joD = new JSONObject(joS);

        Object o1 = jo.get("t");
        Object o2 = joD.get("t");

        int karel = 1;
    }

}
