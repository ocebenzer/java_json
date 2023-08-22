package subvalues;

import json.JSONType;
import json.JSONValue;

public class JSONFalse extends JSONValue {
    public JSONFalse() {
        super(false, JSONType._JSONFalse);
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
