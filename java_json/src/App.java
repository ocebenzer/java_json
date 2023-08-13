import java.util.Scanner;
import java.util.regex.Pattern;

import json.JSON;
import json.ParseException;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("asd");
        System.out.println(scanner.next(Pattern.compile("a")));
    }
}
