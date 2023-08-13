package subtypes;

import json.JSON;

public class JSONInteger extends JSON {
    private int value;

    public JSONInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
