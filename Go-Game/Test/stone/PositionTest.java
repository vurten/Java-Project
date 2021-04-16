package Test.stone;

import go.stone.Position;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionTest {
    private static Position position1 = new Position();
    private static Position position2 = new Position();
    private static Position position3 = new Position();
    private static int x = 1;
    private static int y = 1;

    @BeforeAll
    static void setup() {
        position1.setX(2);
        position1.setY(3);
        position2.setX(2);
        position2.setY(3);
        position3.setX(5);
        position3.setY(5);
    }

    @org.junit.jupiter.api.Test
    void getX() {
        assertEquals(2, position1.getX());
    }

    @org.junit.jupiter.api.Test
    void getY() {
        assertEquals(3, position1.getY());
    }

    @org.junit.jupiter.api.Test
    void setX() {
    }

    @org.junit.jupiter.api.Test
    void setY() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertEquals(position1, position2);
    }

    @org.junit.jupiter.api.Test
    void testNotEquals() {
        assertNotEquals(position1, position3);
        //assertFalse(position1.equals(position3));
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
        assertEquals("(" + 2 + "," + 3 + ")", position1.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString3() {
        assertEquals("(" + 5 + "," + 5 + ")", position3.toString());
    }
}