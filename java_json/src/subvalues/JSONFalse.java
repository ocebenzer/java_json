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

    @Override
    public boolean equals(Object other) {
        return other instanceof JSONFalse;
    }
}
