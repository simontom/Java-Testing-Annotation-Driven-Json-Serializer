package simon.jsonserializer.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import simon.jsonserializer.parser.exceptions.JsonParserException;
import simon.jsonserializer.parser.helpers.FieldInformationExtractor;
import simon.jsonserializer.parser.helpers.TypeChecker;

import static java.util.Objects.requireNonNull;

public class JsonDeserializer {
    private final FieldInformationExtractor fieldInformationExtractor;
    private final TypeChecker typeChecker;

    public JsonDeserializer(FieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        this.fieldInformationExtractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
    }

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        try {
            T instance = clazz.newInstance();

            return instance;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Deserialize object", e);
        }
    }

}
