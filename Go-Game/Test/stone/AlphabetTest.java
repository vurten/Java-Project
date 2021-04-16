package Test.stone;

import go.stone.Alphabet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlphabetTest {

    @org.junit.jupiter.api.Test
    void getEnumValue() {
        Alphabet a = Alphabet.A;
        assertEquals(1, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue1() {
        Alphabet a = Alphabet.B;
        assertEquals(2, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue2() {
        Alphabet a = Alphabet.C;
        assertEquals(3, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue3() {
        Alphabet a = Alphabet.D;
        assertEquals(4, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue4() {
        Alphabet a = Alphabet.E;
        assertEquals(5, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue5() {
        Alphabet a = Alphabet.F;
        assertEquals(6, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue6() {
        Alphabet a = Alphabet.G;
        assertEquals(7, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue7() {
        Alphabet a = Alphabet.H;
        assertEquals(8, a.getEnumValue());
    }

    @org.junit.jupiter.api.Test
    void getEnumValue8() {
        Alphabet a = Alphabet.J;
        assertEquals(9, a.getEnumValue());
    }

}