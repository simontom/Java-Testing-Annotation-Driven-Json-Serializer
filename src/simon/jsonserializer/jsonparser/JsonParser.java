package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeHelper;

public class JsonParser {
    private final JsonSerializer serializer;
    private final JsonDeserializer deserializer;

    public static JsonParser create() {
        FieldInformationExtractor fieldInformationExtractor = new FieldInformationExtractor();
        TypeHelper typeHelper = new TypeHelper();

        return new JsonParser(fieldInformationExtractor, typeHelper);
    }

    private JsonParser(FieldInformationExtractor fieldInformationExtractor, TypeHelper typeHelper) {
        serializer = new JsonSerializer(fieldInformationExtractor, typeHelper);
        deserializer = new JsonDeserializer(fieldInformationExtractor, typeHelper);
    }

    public JSONObject toJson(@NotNull Object toBeJsonified) throws JsonParserException {
        return serializer.serialize(toBeJsonified);
    }

    public <T> T fromJson(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        return (T) deserializer.deserialize(toBeDeJsonified, clazz);
    }

}
