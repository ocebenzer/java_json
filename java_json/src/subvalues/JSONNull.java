package subvalues;

import json.JSONValue;

public class JSONNull extends JSONValue {
    public JSONNull() {
        super(null);
    }

    @Override
    public String toString() {
        return "null";
    }
}
