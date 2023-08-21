package subvalues;

import java.util.ArrayList;
import java.util.HashMap;

import json.JSONValue;

public class JSONArray extends JSONValue {
    public JSONArray(ArrayList<JSONValue> value) {
        super(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder('[');

        ArrayList<JSONValue> values = (ArrayList<JSONValue>) this.get();
        for (JSONValue value : values) {
            sb.append(value.toString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');

        return sb.toString();
    }
}
