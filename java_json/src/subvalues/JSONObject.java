package subvalues;

import java.util.HashMap;

import json.JSONValue;

public class JSONObject extends JSONValue {
    public JSONObject(HashMap<String, JSONValue> value) {
        super(value);
    }
}
