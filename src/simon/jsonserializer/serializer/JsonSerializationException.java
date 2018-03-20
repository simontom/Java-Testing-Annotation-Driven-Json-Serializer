package simon.jsonserializer.serializer;

public class JsonSerializationException extends Exception {
    public JsonSerializationException(String message, Exception e) {
        super(message, e);
    }
}
