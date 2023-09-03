package subvalues;

import json.JSONType;
import json.JSONValue;

public class JSONTrue extends JSONValue {
    public JSONTrue() {
        super(true, JSONType._JSONTrue);
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
