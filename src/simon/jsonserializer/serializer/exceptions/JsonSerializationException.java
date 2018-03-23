package simon.jsonserializer.serializer.exceptions;

public class JsonSerializationException extends Exception {
    public JsonSerializationException(String message, Exception e) {
        super(message, e);
    }
}
