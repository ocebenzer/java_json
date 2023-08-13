import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSON;
import json.ParseException;

import subtypes.JSONString;
import subtypes.JSONBoolean;
import subtypes.JSONNull;

public class JSONScannerTest {
    @Test
    public void testNull() throws ParseException {
        JSON json_null = JSON.parse("null");
        assertTrue(json_null instanceof JSONNull);
    }

    @Test
    public void testBoolean() throws ParseException {
        JSON json_true = JSON.parse("true");

        assertTrue(json_true instanceof JSONBoolean);
        assertEquals(true,
            ((JSONBoolean) json_true).getValue());


        JSON json_false = JSON.parse("false");

        assertTrue(json_false instanceof JSONBoolean);
        assertEquals(false,
            ((JSONBoolean) json_false).getValue());
    }

    @Test
    public void testString() throws ParseException {
        JSON json_emptystring = JSON.parse("\"\"");

        assertTrue(json_emptystring instanceof JSONString);
        assertEquals("",
            ((JSONString) json_emptystring).getValue());

        
        String string_body = "Hello World! This is a \\\"string\\\"";
        JSON json_string = JSON.parse("\"" + string_body + "\"");

        assertTrue(json_string instanceof JSONString);
        assertEquals(string_body,
            ((JSONString) json_string).getValue());
    }
}
