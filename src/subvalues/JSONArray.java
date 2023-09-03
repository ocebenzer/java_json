package subvalues;

import java.util.ArrayList;

import json.JSONType;
import json.JSONValue;

public class JSONArray extends JSONValue {
    public JSONArray(ArrayList<JSONValue> value) {
        super(value, JSONType._JSONArray);
    }

    @Override
    public String toString() {
        ArrayList<JSONValue> values = (ArrayList<JSONValue>) this.get();
        if (values.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (JSONValue value : values) {
            sb.append(value.toString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONArray)) {
            return false;
        }

        ArrayList<JSONValue> v1 = (ArrayList<JSONValue>) this.get();
        ArrayList<JSONValue> v2 = (ArrayList<JSONValue>) ((JSONArray) other).get();
        if (v1.size() != v2.size()) {
            return false;
        }

        for (int i = 0; i < v1.size(); i++) {
            if (!v1.get(i).equals(v2.get(i))) {
                return false;
            }
        }

        return true;
    }
}
