import json.JSONValue;
import json.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        JSONValue json = JSONValue.parse("[1, 2, 3, 4]");
        System.out.println(json);
        return;
    }
}
