package Test.liberty;

import go.liberty.North;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NorthTest {

    @org.junit.jupiter.api.Test
    void isFree() {
        North n = new North(true);
        assertEquals(true, n.isFree());
    }

    @org.junit.jupiter.api.Test
    void isFree1() {
        North n = new North(false);
        assertEquals(false, n.isFree());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        North n = new North(false);
        assertEquals("N(" + false + ")", n.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
        North n = new North(true);
        assertEquals("N(" + true + ")", n.toString());
    }
}