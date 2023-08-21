package subvalues;

import java.util.ArrayList;

import json.JSONValue;

public class JSONString extends JSONValue {
    public JSONString(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "\"" + (String) this.get() + "\"";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONString)) {
            return false;
        }

        String v1 = (String) this.get();
        String v2 = (String) ((JSONString) other).get();
        
        return v1.equals(v2);
    }
}
