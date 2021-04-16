package Test.liberty;

import go.liberty.South;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SouthTest {

    @org.junit.jupiter.api.Test
    void isFree() {
        South s = new South(true);
        assertEquals(true, s.isFree());
    }

    @org.junit.jupiter.api.Test
    void isFree1() {
        South s = new South(false);
        assertEquals(false, s.isFree());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        South s = new South(false);
        assertEquals("S(" + false + ")", s.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
        South s = new South(true);
        assertEquals("S(" + true + ")", s.toString());
    }
}