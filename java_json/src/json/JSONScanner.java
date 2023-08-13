package json;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import subtypes.JSONBoolean;
import subtypes.JSONDictionary;
import subtypes.JSONDouble;
import subtypes.JSONInteger;
import subtypes.JSONList;
import subtypes.JSONNull;
import subtypes.JSONString;

public class JSONScanner {
    private static final Pattern nextChar = Pattern.compile(".");

    private static final Pattern JSONEnd = Pattern.compile("]|}|,");

    private static final Pattern KeyValDelimiter = Pattern.compile(":");

    /// supports +_0, 0e5, .5, 12. 
    private static final Pattern _double = Pattern.compile("(^([+-]?(?:[[:d:]]+\\.?|[[:d:]]*\\.[[:d:]]+))(?:[Ee][+-]?[[:d:]]+)?$)");

    /// between two " not preceded by \
    private static final Pattern _string = Pattern.compile("^\"(.*?)(?<!\\\\)\"");

    private StringReader stringReader;
    private Scanner scanner;

    public JSON parse(String string) throws ParseException {
        stringReader = new StringReader(string);
        scanner = new Scanner(stringReader);

        scanner.useDelimiter(".*");
        JSON result = this.parseJSON();
        
        scanner.close();
        stringReader.close();
        return result;
    }

    /// peek next char without reading
    private char peek() {
        try {
            stringReader.mark(1);
            char c = (char) stringReader.read();
            stringReader.reset();
            return c;
        }
        catch (IOException e) {
            return '\0';
        }
    }

    private JSON parseJSON() throws ParseException {
        char c = peek();
        switch (c) {
            case 'n':
                return parseNull();
            case 't':
            case 'f':
                return parseBoolean();
            case '"':
                return parseString();
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
                return parseNumber();
            case '[':
                return parseList();
            case '{':
                return parseDictionary();
            default:
                throw new ParseException("<JSON_BEGIN>", c);
        }
    }

    private JSON parseDictionary() throws ParseException {
        char c = peek();
        if (c != '{') {
            throw new ParseException("{", c);
        }

        HashMap<String, JSON> dict = new HashMap<>();
        while (true) {
            String key = scanner.next(KeyValDelimiter);

            if (peek() != ':') {
                throw new ParseException(":", c);
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

        return new JSONDictionary(dict);
    }

    private JSON parseList() throws ParseException {
        char c = peek();
        if (c != '[') {
            throw new ParseException("[", c);
        }

        ArrayList<JSON> list = new ArrayList<>();
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
        
        return new JSONList(list);
    }

    private JSON parseNumber() throws ParseException {
        String s = scanner.next();

        if (_double.matcher(s).find()) {
            return new JSONDouble(Double.parseDouble(s));
        }
        return new JSONInteger(Integer.parseInt(s));
    }

    private JSON parseString() throws ParseException {
        String s = scanner.findInLine(_string);
        String value = s.substring(1, s.length() - 1);
        return new JSONString(value);
    }

    private JSON parseBoolean() throws ParseException {
        String s = scanner.next();

        if (s.equals("true")) {
            return new JSONBoolean(true);
        }
        else if (s.equals("false")) {
            return new JSONBoolean(false);
        }

        throw new ParseException("true|false", s);
    }

    private JSON parseNull() throws ParseException {
        return new JSONNull();
    }
}
