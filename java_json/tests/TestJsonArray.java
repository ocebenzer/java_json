import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONArray;
import subvalues.JSONFalse;
import subvalues.JSONNull;
import subvalues.JSONNumber;
import subvalues.JSONObject;
import subvalues.JSONString;

public class TestJsonArray {
    @Test
    public void testEmptyArray() throws ParseException {
        JSONValue json = JSONValue.parse("[]");
        ArrayList<JSONValue> expected = new ArrayList<>();

        assertTrue(json instanceof JSONArray);
        assertEquals(expected, json.get());
    }

    @Test
    public void testEmptyArray2() throws ParseException {
        JSONValue json = JSONValue.parse("[ \n ]");
        ArrayList<JSONValue> expected = new ArrayList<>();

        assertTrue(json instanceof JSONArray);
        assertEquals(expected, json.get());
    }

    @Test
    public void testSmallArray() throws ParseException {
        JSONValue json = JSONValue.parse("[ null ]");
        ArrayList<JSONValue> expected = new ArrayList<>();
        expected.add(new JSONNull());

        assertTrue(json instanceof JSONArray);
        assertEquals(expected, json.get());
    }

    @Test
    public void testLargeArray() throws ParseException {
        JSONValue json = JSONValue.parse("[ 1, 2, 3, 4, 5 ]");
        ArrayList<JSONValue> expected = new ArrayList<>();
        expected.add(new JSONNumber(1));
        expected.add(new JSONNumber(2));
        expected.add(new JSONNumber(3));
        expected.add(new JSONNumber(4));
        expected.add(new JSONNumber(5));

        assertTrue(json instanceof JSONArray);
        assertEquals(expected, json.get());
    }

    @Test
    public void testComplexArray() throws ParseException {
        JSONValue json = JSONValue.parse("[ {\"key\": \"value\"}, 2, \"yes\", false, [] ]");
        ArrayList<JSONValue> expected = new ArrayList<>();
        HashMap<String, JSONValue> e1 = new HashMap<>();
        e1.put("key", new JSONString("value"));
        expected.add(new JSONObject(e1));
        expected.add(new JSONNumber(2));
        expected.add(new JSONString("yes"));
        expected.add(new JSONFalse());
        expected.add(new JSONArray(new ArrayList<JSONValue>()));

        assertTrue(json instanceof JSONArray);
        assertEquals(expected, json.get());
    }
}
