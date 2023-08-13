package subvalues;

import java.util.HashMap;

import json.JSONValue;

public class JSONDictionary extends JSONValue {
    public JSONDictionary(HashMap<String, JSONValue> value) {
        super(value);
    }
}
