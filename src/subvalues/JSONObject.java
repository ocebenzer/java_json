package subvalues;

import java.util.HashMap;

import json.JSONType;
import json.JSONValue;

public class JSONObject extends JSONValue {
    public JSONObject(HashMap<String, JSONValue> value) {
        super(value, JSONType._JSONObject);
    }

    @Override
    public String toString() {
        HashMap<String, JSONValue> values = (HashMap<String, JSONValue>) this.get();
        if (values.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('{');

        for (String key : values.keySet()) {
            JSONValue value = values.get(key);
            sb.append('"');
            sb.append(key);
            sb.append("\":");
            sb.append(value.toString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('}');

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONObject)) {
            return false;
        }

        HashMap<String, JSONValue> v1 = (HashMap<String, JSONValue>) this.get();
        HashMap<String, JSONValue> v2 = (HashMap<String, JSONValue>) ((JSONObject) other).get();
        if (v1.size() != v2.size()) {
            return false;
        }

        for (String key : v1.keySet()) {
            if (!v1.get(key).equals(v2.get(key))) {
                return false;
            }
        }

        return true;
    }
}
