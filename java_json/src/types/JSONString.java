package types;

import java.util.Scanner;

public class JSONString extends JSON {
    private String value;

    public JSONString(Scanner s) {
        super(s);
    }


    public String getValue() {
        return this.value;
    }
}
