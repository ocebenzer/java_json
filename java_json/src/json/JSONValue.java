package json;

public abstract class JSONValue {
    private Object value;

    public static JSONValue parse(String str) throws ParseException {
        JSONScanner jss = new JSONScanner(str);
        return jss.parse();
    }

    protected JSONValue (Object value) {
        this.value = value;
    }

    public Object get() {
        return this.value;
    }
}
