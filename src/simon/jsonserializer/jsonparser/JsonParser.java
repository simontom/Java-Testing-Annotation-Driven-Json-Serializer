package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;

public class JsonParser {
    private final JsonSerializer serializer;
    private final JsonDeserializer deserializer;

    public static JsonParser create() {
        FieldInformationExtractor fieldInformationExtractor = new FieldInformationExtractor();
        TypeChecker typeChecker = new TypeChecker();

        return new JsonParser(fieldInformationExtractor, typeChecker);
    }

    private JsonParser(FieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        serializer = new JsonSerializer(fieldInformationExtractor, typeChecker);
        deserializer = new JsonDeserializer(fieldInformationExtractor, typeChecker);
    }

    public JSONObject toJson(@NotNull Object toBeJsonified) throws JsonParserException {
        return serializer.serialize(toBeJsonified);
    }

    public <T> T fromJson(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        return (T) deserializer.deserialize(toBeDeJsonified, clazz);
    }

}
