import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSON;
import json.ParseException;
import subtypes.JSONBoolean;

public class TestJSONBoolean {
    @Test
    public void testTrue() throws ParseException {
        JSON json = JSON.parse("true");

        assertTrue(json instanceof JSONBoolean);
        assertEquals(true,
            ((JSONBoolean) json).getValue());
    }

    @Test
    public void testFalse() throws ParseException {
        JSON json = JSON.parse("false");

        assertTrue(json instanceof JSONBoolean);
        assertEquals(false,
            ((JSONBoolean) json).getValue());
    }
}
