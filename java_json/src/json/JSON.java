package json;

public abstract class JSON {
    public static JSON parse(String str) throws ParseException {
        JSONScanner jss = new JSONScanner();
        return jss.parse(str);
    }
}
