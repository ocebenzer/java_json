import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONFalse;
import subvalues.JSONNull;
import subvalues.JSONObject;
import subvalues.JSONTrue;

public class TestJSONObject {
    @Test
    public void TestEmptyObject() throws ParseException {
        JSONValue json = JSONValue.parse("{}");
        HashMap<String, JSONValue> expected = new HashMap<>();

        assertTrue(json instanceof JSONObject);
        assertEquals(expected, json.get());
    }

    @Test
    public void TestEmptyObject2() throws ParseException {
        JSONValue json = JSONValue.parse(" { \n } ");
        HashMap<String, JSONValue> expected = new HashMap<>();

        assertTrue(json instanceof JSONObject);
        assertEquals(expected, json.get());
    }

    @Test
    public void TestSmallObject() throws ParseException {
        JSONValue json = JSONValue.parse(" { \"\": null } ");
        HashMap<String, JSONValue> expected = new HashMap<>();
        expected.put("", new JSONNull());

        assertTrue(json instanceof JSONObject);
        assertEquals(expected, json.get());
    }

    @Test
    public void TestLargeObject() throws ParseException {
        JSONValue json = JSONValue.parse(" { \"1\": false, \"2\": true, \"3\": false, \"4\": true, \"5\": false } ");
        HashMap<String, JSONValue> expected = new HashMap<>();
        expected.put("1", new JSONFalse());
        expected.put("2", new JSONTrue());
        expected.put("3", new JSONFalse());
        expected.put("4", new JSONTrue());
        expected.put("5", new JSONFalse());

        assertTrue(json instanceof JSONObject);
        assertEquals(expected, json.get());
    }

    @Test
    public void TestComplexObject() throws ParseException {
        JSONValue json = JSONValue.parse(" { \"1\" : false , \"2\": [], \"3\": { \" \" : null } , \n\"4\": [null], \"5\": [3,4,5,6,7] } ");
        HashMap<String, JSONValue> expected = new HashMap<>();

        assertTrue(json instanceof JSONObject);
        assertEquals(expected, json.get());
    }
}
