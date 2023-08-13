package subtypes;

import json.JSON;

public class JSONBoolean extends JSON {
    private boolean value;

    public JSONBoolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}
