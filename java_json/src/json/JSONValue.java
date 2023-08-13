package json;

public abstract class JSONValue {
    private Object value;

    public static JSONValue parse(String str) throws ParseException {
        JSONScanner jss = new JSONScanner();
        return jss.parse(str);
    }

    protected JSONValue (Object value) {
        this.value = value;
    }

    public Object get() {
        return this.value;
    }
}
