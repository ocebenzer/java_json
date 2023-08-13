package subtypes;

import json.JSON;

public class JSONNumber extends JSON {
    private double value;

    public JSONNumber(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
