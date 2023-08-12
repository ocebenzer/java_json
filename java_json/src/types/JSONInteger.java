package types;

import java.util.Scanner;

public class JSONInteger extends JSON {
    private int value;

    public JSONInteger(Scanner s) {
        super(s);
        value = s.nextInt();
    }


    public int getValue() {
        return this.value;
    }
}
