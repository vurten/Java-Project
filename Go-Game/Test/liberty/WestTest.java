package Test.liberty;

import go.liberty.West;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WestTest {

    @org.junit.jupiter.api.Test
    void isFree() {
        West w = new West(true);
        assertEquals(true, w.isFree());
    }

    @org.junit.jupiter.api.Test
    void isFree1() {
        West w = new West(false);
        assertEquals(false, w.isFree());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        West w = new West(false);
        assertEquals("W(" + false + ")", w.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
        West w = new West(true);
        assertEquals("W(" + true + ")", w.toString());
    }
}