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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONNumber)) {
            return false;
        }

        Double v1 = (Double) this.get();
        Double v2 = (Double) ((JSONNumber) other).get();
        
        return v1.equals(v2);
    }
}
