package subvalues;

import json.JSONValue;

public class JSONNumber extends JSONValue {
    public JSONNumber(double value) {
        super(value);
    }

    @Override
    public String toString() {
        Double value = (Double) this.get();
        // todo
        // if (value) return Integer.toString((Integer) value)
        return Double.toString((Double) value);
    }
}
