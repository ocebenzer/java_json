package types;

import java.util.Scanner;

public abstract class JSON {
    JSON(Scanner s) { }

    public static JSON parse(String str) {
        Scanner s = new Scanner(str);
        return new JSONDictionary(s);
    }
}
