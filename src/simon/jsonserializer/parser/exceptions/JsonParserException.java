package simon.jsonserializer.parser.exceptions;

public class JsonParserException extends Exception {
    public JsonParserException(String message, Exception e) {
        super(message, e);
    }
}
