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

    @Override
    public boolean equals(Object other) {
        return other instanceof JSONNull;
    }
}
