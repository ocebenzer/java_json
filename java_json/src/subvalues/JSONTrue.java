package subvalues;

import json.JSONValue;

public class JSONTrue extends JSONValue {
    public JSONTrue() {
        super(true);
    }

    @Override
    public String toString() {
        return "true";
    }
}
