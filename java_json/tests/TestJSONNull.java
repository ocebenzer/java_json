import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSON;
import json.ParseException;

import subtypes.JSONNull;

public class TestJSONNull {
    @Test
    public void testNull() throws ParseException {
        JSON json = JSON.parse("null");
        assertTrue(json instanceof JSONNull);
    }
}
