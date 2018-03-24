package simon.jsonserializer.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

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

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) {
        return null;
    }

}
