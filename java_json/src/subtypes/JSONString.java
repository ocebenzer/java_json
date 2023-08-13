package subtypes;

import json.JSON;

public class JSONString extends JSON {
    private String value;

    public JSONString(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
