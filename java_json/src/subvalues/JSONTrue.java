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

    @Override
    public boolean equals(Object other) {
        return other instanceof JSONTrue;
    }
}
