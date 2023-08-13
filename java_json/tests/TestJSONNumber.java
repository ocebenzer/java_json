import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSON;
import json.ParseException;
import subtypes.JSONNumber;


public class TestJSONNumber {
    private void assertDoubleEquals(double expected, double actual) {
        assertEquals(expected, actual, 0.0000001);
    }
    @Test
    public void testZero() throws ParseException {
        JSON json = JSON.parse("0");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(0,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testPositiveInteger() throws ParseException {
        JSON json = JSON.parse("3");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(3,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testNegativeInteger() throws ParseException {
        JSON json = JSON.parse("-2");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(-2,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testZeroDouble() throws ParseException {
        JSON json = JSON.parse("0.0");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(0.0,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testPositiveDouble() throws ParseException {
        JSON json = JSON.parse("42.5");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(42.5,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testNegativeDouble() throws ParseException {
        JSON json = JSON.parse("-37.62");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(-37.62,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testExp() throws ParseException {
        JSON json = JSON.parse("10e2");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(10e2,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testNegativeExp() throws ParseException {
        JSON json = JSON.parse("6.62e-34");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(6.62e-34,
            ((JSONNumber) json).getValue());
    }

    @Test
    public void testNegativeExp2() throws ParseException {
        JSON json = JSON.parse("-10.0e+8");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(-10.0e8,
            ((JSONNumber) json).getValue());
    }
}
