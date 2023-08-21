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
    private String string;
    private int index;

    public JSONScanner(String string) {
        this.string = string;
        this.index = 0;
    }

    public JSONValue parse() throws ParseException {
        return this.parseValue();
    }

    private char peek() {
        return this.string.charAt(index);
    }

    private char peek(int customIndex) {
        return this.string.charAt(customIndex);
    }

    private char read1() {
        return this.string.charAt(index++);
    }

    private void trimWhitespace() {
        for (char c = peek();
                c == ' ' || c == '\n' || c == '\t' || c == ' ';
                c = this.peek()) {
            index++;
        }
    }

    /// LL1 Parsing - First(1) Lookahead Table
    private JSONValue parseValue() throws ParseException {
        char c = this.peek();
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
            // case '.': // Not in JSON Standarts
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
        int c = this.read1();
        if (c != '{') {
            throw new ParseException("{", (char) c);
        }

        HashMap<String, JSONValue> dict = new HashMap<>();

        trimWhitespace();
        for (c = this.peek(); c != '}'; c = this.peek()) {
            String key = parseString();

            c = this.read1();
            if (c != ':') {
                throw new ParseException(":", (char) c);
            }

            JSONValue value = parseValue();
            dict.put(key, value);

            c = peek();
            if (c != ',' && c != '}') {
                throw new ParseException(",|}", (char) c);
            }
        }

        return dict;
    }

    private ArrayList<JSONValue> parseList() throws ParseException {
        int c = read1();
        if (c != '[') {
            throw new ParseException("[", (char) c);
        }

        c = read1();

        ArrayList<JSONValue> list = new ArrayList<>();

        while (c != ']') {
            JSONValue value = parseValue();
            list.add(value);

            c = peek();
            if (c != ',' && c != ']') {
                throw new ParseException(",|]", (char) c);
            }
        }

        return list;
    }

    private Double parseNumber() throws ParseException {
        int indexEnd = this.index;
        char c = this.peek(indexEnd);

        // number
        if (c == '-') {
            c = this.peek(indexEnd++);
        }

        if (c == '0') {
            c = this.peek(indexEnd++);
        }
        else {
            if (c <= '0' || c > '9') {
                throw new ParseException("[1-9]", c);
            }
            while (c >= '0' && c <= '9') {
                c = this.peek(indexEnd++);
            }
        }

        // fraction
        if (this.peek(indexEnd) == '.') {
            c = this.peek(indexEnd++);
            while (c >= '0' && c <= '9') {
                c = this.peek(indexEnd++);
            }
        }

        // exponent
        if (c == 'e' || c == 'E') {
            c = this.peek(indexEnd++);
            if (c == '+' || c == '-') {
                c = this.peek(indexEnd++);
            }
            while (c >= '0' && c <= '9') {
                c = this.peek(indexEnd++);
            }
        }

        String extractedString = this.string.substring(index, indexEnd);
        return Double.valueOf(extractedString);
    }

    private String parseString() throws ParseException {
        int indexEnd = this.index;
        char c = this.peek(indexEnd);
        boolean is_escape_character = false;

        if (c != '"') {
            throw new ParseException("\"", c);
        }

        while (is_escape_character || c != '"') {
            if (c == '\\') {
                is_escape_character = true;
            }
            indexEnd++;
        }

        String extractedString = this.string.substring(index, indexEnd);
        return extractedString;
    }
}
