package subtypes;

import java.util.ArrayList;

import json.JSON;

public class JSONList extends JSON {
    private ArrayList<JSON> value;

    public JSONList(ArrayList<JSON> value) {
        this.value = value;
    }

    public ArrayList<JSON> getValue() {
        return this.value;
    }
}
