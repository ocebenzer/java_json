package subtypes;

import java.util.HashMap;

import json.JSON;

public class JSONDictionary extends JSON {
    private HashMap<String, JSON> value;

    public JSONDictionary(HashMap<String, JSON> value) {
        this.value = value;
    }

    public HashMap<String, JSON> getValue() {
        return this.value;
    }
}
