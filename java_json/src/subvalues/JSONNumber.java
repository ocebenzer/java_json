package subvalues;

import json.JSONValue;

public class JSONNumber extends JSONValue {
    private boolean is_integer = false;

    public JSONNumber(double value) {
        super(value);
    }

    public JSONNumber(double value, boolean is_integer) {
        super(value);
        this.is_integer = is_integer;
    }

    @Override
    public String toString() {
        Double value = (Double) this.get();
        if (is_integer) {
            return Integer.toString(value.intValue());
        }
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
