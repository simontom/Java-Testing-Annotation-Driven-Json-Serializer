package simon.jsonserializer.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.parser.exceptions.JsonSerializationException;
import simon.jsonserializer.parser.helpers.FieldInformationExtractor;
import simon.jsonserializer.parser.helpers.TypeChecker;

public class ParserFacade {
    private final JsonSerializer serializer;
    private final JsonDeserializer deserializer;

    public ParserFacade() {
        FieldInformationExtractor fieldInformationExtractor = new FieldInformationExtractor();
        TypeChecker typeChecker = new TypeChecker();

        serializer = new JsonSerializer(fieldInformationExtractor, typeChecker);
        deserializer = new JsonDeserializer(fieldInformationExtractor, typeChecker);
    }

    public JSONObject serialize(@NotNull Object toBeJsonified) throws JsonSerializationException {
        return serializer.serialize(toBeJsonified);
    }

    public <T> T deserialize(JSONObject toBeDeJsonified, Class<T> clazz) {
        return (T) deserializer.deserialize(toBeDeJsonified, clazz);
    }

}
