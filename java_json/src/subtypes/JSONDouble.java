package subtypes;

import json.JSON;

public class JSONDouble extends JSON {
    private double value;

    public JSONDouble(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
