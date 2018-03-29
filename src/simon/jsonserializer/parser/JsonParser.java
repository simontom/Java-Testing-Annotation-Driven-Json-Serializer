package simon.jsonserializer.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.parser.exceptions.JsonParserException;
import simon.jsonserializer.parser.helpers.FieldInformationExtractor;
import simon.jsonserializer.parser.helpers.TypeChecker;

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


    public JSONObject serialize(@NotNull Object toBeJsonified) throws JsonParserException {
        return serializer.serialize(toBeJsonified);
    }

    public <T> T deserialize(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        return (T) deserializer.deserialize(toBeDeJsonified, clazz);
    }

}
