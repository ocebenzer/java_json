package types;

import java.util.Scanner;

public class JSONBoolean extends JSON {
    private boolean value;

    public JSONBoolean(Scanner s) {
        super(s);
    }

    public boolean getValue() {
        return this.value;
    }
}
