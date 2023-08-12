package types;

import java.util.ArrayList;
import java.util.Scanner;

public class JSONList extends JSON {
    private ArrayList<JSON> value;

    public JSONList(Scanner s) {
        super(s);
    }

    public ArrayList<JSON> getValue() {
        return this.value;
    }
}
