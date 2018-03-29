package simon.jsonserializer.jsonparser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import simon.jsonserializer.jsonparser.exceptions.JsonParserException;
import simon.jsonserializer.jsonparser.helpers.FieldInformation;
import simon.jsonserializer.jsonparser.helpers.FieldInformationExtractor;
import simon.jsonserializer.jsonparser.helpers.TypeChecker;

import static java.util.Objects.requireNonNull;

public class JsonDeserializer {
    private final FieldInformationExtractor extractor;
    private final TypeChecker typeChecker;

    public JsonDeserializer(FieldInformationExtractor fieldInformationExtractor, TypeChecker typeChecker) {
        this.extractor = fieldInformationExtractor;
        this.typeChecker = typeChecker;
    }

    public <T> T deserialize(@NotNull JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        requireNonNull(toBeDeJsonified);
        return deserializeHelper(toBeDeJsonified, clazz);
    }

    private <T> T deserializeHelper(JSONObject toBeDeJsonified, Class<T> clazz) throws JsonParserException {
        try {
            T instance = clazz.newInstance();
            List<FieldInformation> fieldInformationList = extractor.extractFieldInformations(instance);
            for (FieldInformation fieldInformation : extractor.extractFieldInformations(instance)) {
                Object jsonData;
                if (fieldInformation.isOptional) {
                    jsonData = toBeDeJsonified.opt(fieldInformation.name);
                    if (jsonData == null) {
                        continue;
                    }
                }
                else {
                    jsonData = toBeDeJsonified.get(fieldInformation.name);
                }

                // TODO: Add deserialization logic
            }

            return instance;
        }
        catch (Exception e) {
            throw new JsonParserException("Unable to Deserialize object", e);
        }
    }

}
