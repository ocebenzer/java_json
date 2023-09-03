package json;

import java.util.ArrayList;
import java.util.HashMap;

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

    protected JSONScanner(String string) {
        this.string = string;
        this.index = 0;
    }

    protected JSONValue parse() throws ParseException {
        return this.parseValue();
    }

    private char peek() {
        if (index >= this.string.length()) {
            return '\0';
        }
        return this.string.charAt(index);
    }

    private char peek(int customIndex) {
        if (customIndex >= this.string.length()) {
            return '\0';
        }
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
        trimWhitespace();
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
                return parseNumber();
            case 't':
                parseTrue();
                return new JSONTrue();
            case 'f':
                parseFalse();
                return new JSONFalse();
            case 'n':
                parseNull();
                return new JSONNull();
            default:
                throw new ParseException("<JSON_BEGIN>", c);
        }
    }

    /*
     * Object :- '{' <whitespace> '}'
     * Object :- '{' <(<Key>:<Value>)+> '}'
     */
    private HashMap<String, JSONValue> parseObject() throws ParseException {
        char c = this.read1();
        if (c != '{') {
            throw new ParseException("{", (char) c);
        }

        HashMap<String, JSONValue> dict = new HashMap<>();

        trimWhitespace();
        c = this.peek();
        while (c != '}') {
            trimWhitespace();
            String key = parseString();

            trimWhitespace();
            c = this.read1();
            if (c != ':') {
                throw new ParseException(":", (char) c);
            }

            JSONValue value = parseValue();
            dict.put(key, value);

            trimWhitespace();
            c = read1();
            if (c != ',' && c != '}') {
                throw new ParseException(",|}", (char) c);
            }
        }

        return dict;
    }

    private ArrayList<JSONValue> parseList() throws ParseException {
        char c = this.read1();
        if (c != '[') {
            throw new ParseException("[", (char) c);
        }

        ArrayList<JSONValue> list = new ArrayList<>();

        trimWhitespace();
        c = this.peek();
        while (c != ']') {
            JSONValue value = parseValue();
            list.add(value);

            trimWhitespace();
            c = this.read1();
            switch (c) {
                case ']':
                    index--;
                case ',':
                    continue;
                default:
                    throw new ParseException(",|]", (char) c);
            }
        }

        index++;
        return list;
    }

    private String parseString() throws ParseException {
        this.index++;
        int indexEnd = this.index;
        boolean is_escape_character = false;

        for (char c = this.peek(indexEnd++); is_escape_character || c != '"'; c = this.peek(indexEnd++)) {
            is_escape_character = !is_escape_character && c == '\\';
        }

        String extractedString = this.string.substring(index, indexEnd - 1);
        index = indexEnd;
        return extractedString;
    }

    private JSONNumber parseNumber() throws ParseException {
        int indexEnd = this.index;
        char c = this.peek(indexEnd++);

        boolean is_integer = true;

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
        if (c == '.') {
            is_integer = false;
            c = this.peek(indexEnd++);
            while (c >= '0' && c <= '9') {
                c = this.peek(indexEnd++);
            }
        }

        // exponent
        if (c == 'e' || c == 'E') {
            is_integer = false;
            c = this.peek(indexEnd++);
            if (c == '+' || c == '-') {
                c = this.peek(indexEnd++);
            }
            while (c >= '0' && c <= '9') {
                c = this.peek(indexEnd++);
            }
        }

        String extractedString = this.string.substring(index, indexEnd - 1);
        index = indexEnd - 1;

        if (is_integer) {
            return new JSONNumber(Integer.valueOf(extractedString));
        }
        return new JSONNumber(Double.valueOf(extractedString));
    }

    private void parseTrue() throws ParseException {
        String value = string.substring(index, index + 4);
        if (!value.equals("true")) {
            throw new ParseException("true", value);
        }

        index += 4;
    }

    private void parseFalse() throws ParseException {
        String value = string.substring(index, index + 5);
        if (!value.equals("false")) {
            throw new ParseException("false", value);
        }

        index += 5;
    }

    private void parseNull() throws ParseException {
        String value = string.substring(index, index + 4);
        if (!value.equals("null")) {
            throw new ParseException("null", value);
        }

        index += 4;
    }
}
