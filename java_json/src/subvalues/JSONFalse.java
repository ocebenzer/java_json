package subvalues;

import json.JSONValue;

public class JSONFalse extends JSONValue {
    public JSONFalse() {
        super(false);
    }

    @Override
    public String toString() {
        return "false";
    }
}
