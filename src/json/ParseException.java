package json;

public class ParseException extends Exception {
    public ParseException(String error_message) {
        super(error_message);
    }

    public ParseException(String expected, char found) {
        super("expected " + expected + " found " + found);
    }

    public ParseException(String expected, String found) {
        super("expected " + expected + " found " + found);
    }
}
