import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONFalse;
import subvalues.JSONTrue;

public class TestJSONBoolean {
    @Test
    public void testTrue() throws ParseException {
        JSONValue json = JSONValue.parse("true");

        assertTrue(json instanceof JSONTrue);
        assertEquals(true, json.get());
    }

    @Test
    public void testFalse() throws ParseException {
        JSONValue json = JSONValue.parse("false");

        assertTrue(json instanceof JSONFalse);
        assertEquals(false, json.get());
    }
}
