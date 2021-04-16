package Test.liberty;

import go.liberty.East;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EastTest {

    @org.junit.jupiter.api.Test
    void isFree() {
        East e = new East(true);
        assertEquals(true, e.isFree());
    }

    @org.junit.jupiter.api.Test
    void isFree1() {
        East e = new East(false);
        assertEquals(false, e.isFree());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        East e = new East(false);
        assertEquals("E(" + false + ")", e.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
        East e = new East(true);
        assertEquals("E(" + true + ")", e.toString());
    }
}