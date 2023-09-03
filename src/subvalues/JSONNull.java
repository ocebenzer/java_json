package subvalues;

import json.JSONType;
import json.JSONValue;

public class JSONNull extends JSONValue {
    public JSONNull() {
        super(null, JSONType._JSONNull);
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
