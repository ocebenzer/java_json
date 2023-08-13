import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSON;
import json.ParseException;

import subtypes.JSONString;

public class TestJSONString {
    @Test
    public void testEmptyString() throws ParseException {
        JSON json = JSON.parse("\"\"");

        assertTrue(json instanceof JSONString);
        assertEquals("",
            ((JSONString) json).getValue());
    }

    @Test
    public void testSimpleString() throws ParseException {
        String string_body = "According to all known laws of aviation, there is no way that a bee should be able to fly.";
        JSON json = JSON.parse("\"" + string_body + "\"");

        assertTrue(json instanceof JSONString);
        assertEquals(string_body,
            ((JSONString) json).getValue());
    }

    @Test
    public void testMultilineString() throws ParseException {
        String string_body = "Hello World!\nThis is a \\\"string\\\"\n";
        JSON json = JSON.parse("\"" + string_body + "\"");

        assertTrue(json instanceof JSONString);
        assertEquals(string_body,
            ((JSONString) json).getValue());
    }
}
