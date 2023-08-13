package json;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import subvalues.JSONTrue;
import subvalues.JSONObject;
import subvalues.JSONFalse;
import subvalues.JSONArray;
import subvalues.JSONNull;
import subvalues.JSONNumber;
import subvalues.JSONString;

public class JSONScanner {
    private static final Pattern whitespace = Pattern.compile("[\\n\\t ]*");

    private static final Pattern JSONEnd = Pattern.compile("^(]|}|,)");

    private static final Pattern KeyValDelimiter = Pattern.compile("^:");

    /// between two " not preceded by \
    private static final Pattern _string = Pattern.compile("^(\"(.*?)(?<!\\\\)\")", Pattern.DOTALL);

    /// supports +_0, 0e5, .5, 12. 
    private static final Pattern _number = Pattern.compile("^(-?(0|([1-9][0-9]*))(.[0-9]+)?([eE][+-]?[0-9]+)?)");

    private StringReader stringReader;
    private Scanner scanner;

    public JSONValue parse(String string) throws ParseException {
        stringReader = new StringReader(string);
        scanner = new Scanner(stringReader);

        scanner.useDelimiter(".*");
        trimWhitespace();
        JSONValue result = this.parseJSON();
        
        scanner.close();
        stringReader.close();
        return result;
    }

    /// return next char without moving forward
    private char peek() throws ParseException {
        try {
            stringReader.mark(1);
            char c = (char) stringReader.read();
            stringReader.reset();
            return c;
        }
        catch (IOException e) {
            throw new ParseException(e.getLocalizedMessage());
        }
    }

    private char read1() throws ParseException {
        try {
            char c = (char) stringReader.read();
            return c;
        }
        catch (IOException e) {
            throw new ParseException(e.getLocalizedMessage());
        }
    }

    private void trimWhitespace() {
        scanner.skip(whitespace);
    }

    /// LL1 Parsing - First(1) Lookahead Table
    private JSONValue parseJSON() throws ParseException {
        char c = peek();
        switch (c) {
            case '{':
                return new JSONObject(parseObject());
            case '[':
                return new JSONArray(parseList());
            case '"':
                return new JSONString(parseString());
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return new JSONNumber(parseNumber());
            case 't':
                return new JSONTrue();
            case 'f':
                return new JSONFalse();
            case 'n':
                return new JSONNull();
            default:
                throw new ParseException("Unexpected character: " + c);
        }
    }

    /*
     * Object :- '{' <whitespace> '}'
     * Object :- '{' <(<Key>:<Value>)+> '}'
     */
    private HashMap<String, JSONValue> parseObject() throws ParseException {
        int c = read1();
        if (c != '{') {
            throw new ParseException("{", (char) c);
        }
        if (c == -1) {
            throw new ParseException("{", "EOF");
        }

        HashMap<String, JSONValue> dict = new HashMap<>();

        trimWhitespace();
        for (c = peek(); c != '}'; c = peek()) {
            String key = scanner.next(KeyValDelimiter);

            if (peek() != ':') {
                throw new ParseException(":", (char) c);
            }
            
            dict.put(key, parseJSON());

            String delimiter = scanner.findInLine(JSONEnd);
            if (delimiter.equals("}")) {
                break;
            }
            else if (delimiter.equals(",")) {
                continue;
            }
            else {
                throw new ParseException(",|}", delimiter);
            }
        }

        // consume '}'
        c = read1();
        trimWhitespace();

        return dict;
    }

    private ArrayList<JSONValue> parseList() throws ParseException {
        char c = peek();
        if (c != '[') {
            throw new ParseException("[", c);
        }

        ArrayList<JSONValue> list = new ArrayList<>();
        while (true) {
            String s = scanner.next(JSONEnd);
            list.add(parseJSON());

            String delimiter = scanner.findInLine(JSONEnd);
            if (delimiter.equals("]")) {
                break;
            }
            else if (delimiter.equals(",")) {
                continue;
            }
            else {
                throw new ParseException(",|]", delimiter);
            }
        }
        
        return list;
    }

    private Double parseNumber() throws ParseException {
        String s = scanner.findInLine(_number);
        if (s != null) {
            return Double.parseDouble(s);
        }

        throw new ParseException("<NUMBER>", s);
    }

    private String parseString() throws ParseException {
        String s = scanner.findWithinHorizon(_string, Integer.MAX_VALUE);
        if (s == null) {
            throw new ParseException("\"<STRING>\"", s);
        }
        String value = s.substring(1, s.length() - 1);
        return value;
    }
}
