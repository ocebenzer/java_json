import json.JSONValue;
import json.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        JSONValue json = JSONValue.parse("{ \"true\": false, \"\": null    }");
        System.out.println(json);
        return;
    }
}
