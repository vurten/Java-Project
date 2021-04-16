package Test.stone;

import go.stone.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

    @org.junit.jupiter.api.Test
    void getEnumValue() {
        Color c = Color.NOIR;

        assertEquals("Noir", c.getColor());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue1() {
        Color c = Color.BLANC;

        assertEquals("Blanc", c.getColor());
    }
}