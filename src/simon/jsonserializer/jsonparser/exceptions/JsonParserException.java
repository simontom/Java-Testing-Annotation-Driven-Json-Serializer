package simon.jsonserializer.jsonparser.exceptions;

public class JsonParserException extends Exception {
    public JsonParserException(String message, Exception e) {
        super(message, e);
    }
}
