package json;

public abstract class JSONValue {
    private Object value;
    private JSONType type;

    public static JSONValue parse(String str) throws ParseException {
        JSONScanner jss = new JSONScanner(str);
        return jss.parse();
    }

    protected JSONValue (Object value, JSONType type) {
        this.value = value;
        this.type = type;
    }

    public Object get() {
        return this.value;
    }

    public JSONType getType() {
        return this.type;
    }
}
