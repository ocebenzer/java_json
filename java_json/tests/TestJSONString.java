import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONString;

public class TestJSONString {
    @Test
    public void testEmptyString() throws ParseException {
        JSONValue json = JSONValue.parse("\"\"");

        assertTrue(json instanceof JSONString);
        assertEquals("", json.get());
    }

    @Test
    public void testSimpleString() throws ParseException {
        String string_body = "According to all known laws of aviation, there is no way that a bee should be able to fly.";
        JSONValue json = JSONValue.parse("\"" + string_body + "\"");

        assertTrue(json instanceof JSONString);
        assertEquals(string_body, json.get());
    }

    @Test
    public void testMultilineString() throws ParseException {
        String string_body = "Hello World!\nThis is a \\\"string\\\"\n";
        JSONValue json = JSONValue.parse("\"" + string_body + "\"");

        assertTrue(json instanceof JSONString);
        assertEquals(string_body, json.get());
    }
}
