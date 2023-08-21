package subvalues;

import json.JSONValue;

public class JSONString extends JSONValue {
    public JSONString(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "\"" + (String) this.get() + "\"";
    }
}
