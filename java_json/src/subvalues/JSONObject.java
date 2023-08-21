package subvalues;

import java.util.HashMap;

import json.JSONValue;

public class JSONObject extends JSONValue {
    public JSONObject(HashMap<String, JSONValue> value) {
        super(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder('{');

        HashMap<String, JSONValue> values = (HashMap<String, JSONValue>) this.get();
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
}
