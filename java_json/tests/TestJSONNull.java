import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONNull;

public class TestJSONNull {
    @Test
    public void testNull() throws ParseException {
        JSONValue json = JSONValue.parse("null");
        assertTrue(json instanceof JSONNull);
    }
}
