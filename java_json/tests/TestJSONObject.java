import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONObject;

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
}
