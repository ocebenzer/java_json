package types;

import java.util.Scanner;

public class JSONDouble extends JSON {
    private double value;

    public JSONDouble(Scanner s) {
        super(s);
    }

    public double getValue() {
        return this.value;
    }
}
