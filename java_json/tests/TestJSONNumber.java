import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import json.JSONValue;
import json.ParseException;
import subvalues.JSONNumber;


public class TestJSONNumber {
    private void assertDoubleEquals(double expected, Object actual) {
        assertEquals(expected, (double) actual, 0.0000001);
    }
    @Test
    public void testZero() throws ParseException {
        JSONValue json = JSONValue.parse("0");

        assertTrue(json instanceof JSONNumber);
        assertEquals(0, json.get());
    }

    @Test
    public void testNegativeZero() throws ParseException {
        JSONValue json = JSONValue.parse("0");

        assertTrue(json instanceof JSONNumber);
        assertEquals(-0, json.get());
    }

    @Test
    public void testNegativeZero2() throws ParseException {
        JSONValue json = JSONValue.parse("0.0");

        assertTrue(json instanceof JSONNumber);
        assertEquals(-0.0, json.get());
    }

    @Test
    public void testPositiveInteger() throws ParseException {
        JSONValue json = JSONValue.parse("3");

        assertTrue(json instanceof JSONNumber);
        assertEquals(3,
            ((JSONNumber) json).get());
    }

    @Test
    public void testNegativeInteger() throws ParseException {
        JSONValue json = JSONValue.parse("-2");

        assertTrue(json instanceof JSONNumber);
        assertEquals(-2,
            ((JSONNumber) json).get());
    }

    @Test
    public void testZeroDouble() throws ParseException {
        JSONValue json = JSONValue.parse("0.0");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(0.0,
            ((JSONNumber) json).get());
    }

    @Test
    public void testPositiveDouble() throws ParseException {
        JSONValue json = JSONValue.parse("42.5");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(42.5,
            ((JSONNumber) json).get());
    }

    @Test
    public void testNegativeDouble() throws ParseException {
        JSONValue json = JSONValue.parse("-37.62");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(-37.62, json.get());
    }

    @Test
    public void testExp() throws ParseException {
        JSONValue json = JSONValue.parse("10e2");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(10e2, json.get());
    }

    @Test
    public void testNegativeExp() throws ParseException {
        JSONValue json = JSONValue.parse("6.62e-34");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(6.62e-34, json.get());
    }

    @Test
    public void testNegativeExp2() throws ParseException {
        JSONValue json = JSONValue.parse("-10.0e+8");

        assertTrue(json instanceof JSONNumber);
        assertDoubleEquals(-10.0e8, json.get());
    }

    @Test
    public void testIntegerVsDouble() throws ParseException {
        JSONValue json = JSONValue.parse("3");

        assertTrue(json instanceof JSONNumber);
        assertNotEquals(3.0, json.get());
    }
}
