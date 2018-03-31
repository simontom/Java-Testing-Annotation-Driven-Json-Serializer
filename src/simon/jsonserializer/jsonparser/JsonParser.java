package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;
import simon.jsonserializer.jsonparser.helpers.TypeCreator;

public class JsonParser {
    private final JsonSerializer serializer;
    private final JsonDeserializer deserializer;

    public static JsonParser create() {
        FieldInformationExtractor fieldInformationExtractor = new FieldInformationExtractor();
        TypeChecker typeChecker = new TypeChecker();
        TypeCreator typeCreator = new TypeCreator();

        return new JsonParser(fieldInformationExtractor, typeChecker, typeCreator);
    }

    private JsonParser(FieldInformationExtractor fieldInformationExtractor,
                       TypeChecker typeChecker, TypeCreator typeCreator) {

        serializer = new JsonSerializer(fieldInformationExtractor, typeChecker);
        deserializer = new JsonDeserializer(fieldInformationExtractor, typeChecker, typeCreator);
    }

    public JSONObject toJson(@NotNull Object toBeJsonified) throws JsonParserException {
        return serializer.serialize(toBeJsonified);
    }

    public <T> T fromJson(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        return (T) deserializer.deserialize(toBeDeJsonified, clazz);
    }

}
