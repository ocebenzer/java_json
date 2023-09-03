package subvalues;

import json.JSONType;
import json.JSONValue;
import json.ParseException;

public class JSONNumber extends JSONValue {
    public JSONNumber(double value) {
        super(value, JSONType._JSONNumber);
    }

    public JSONNumber(int value) {
        super(value, JSONType._JSONInteger);
    }

    @Override
    public String toString() {
        Double value = (Double) this.get();
        if (this.getType().equals(JSONType._JSONInteger)) {
            return Integer.toString(value.intValue());
        }
        return Double.toString((Double) value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONNumber)) {
            return false;
        }
        JSONNumber number = (JSONNumber) other;

        // 3.0 does not equal to 3
        if (!this.getType().equals(number.getType())) {
            return false;
        }

        return this.get().equals(number.get());
    }
}
