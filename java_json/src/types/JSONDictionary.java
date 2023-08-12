package types;

import java.util.Scanner;
import java.util.HashMap;

public class JSONDictionary extends JSON {
    private HashMap<String, JSON> value;

    public JSONDictionary(Scanner s) {
        super(s);
        value = new HashMap<>();
        s.next("{");
        while (true) {

        }
    }

    public HashMap<String, JSON> getValue() {
        return this.value;
    }
}
