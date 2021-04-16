package Test.stone;

import go.liberty.*;
import go.stone.Color;
import go.stone.Position;
import go.stone.Stone;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StoneTest {

    @org.junit.jupiter.api.Test
    void getColor() {
        Stone s = new Stone(1, 1, Color.NOIR);
        assertEquals(Color.NOIR, s.getColor());
    }

    @org.junit.jupiter.api.Test
    void getColor1() {
        Stone s = new Stone(1, 1, Color.BLANC);
        assertEquals(Color.BLANC, s.getColor());
    }

    @org.junit.jupiter.api.Test
    void getPosition() {
        Stone s = new Stone(1, 1, Color.NOIR);
        Position p = new Position();
        p.setX(1);
        p.setY(1);
        assertEquals(p, s.getPosition());
    }

    @org.junit.jupiter.api.Test
    void getLiberties() {
        Stone s = new Stone(1, 1, Color.NOIR);
        North n = new North(true);
        assertEquals(n.toString(), s.getLiberties().get(0).toString());
    }

    @org.junit.jupiter.api.Test
    void getLiberties3() {
        Stone s = new Stone(1, 1, Color.NOIR);
        South south = new South(true);
        assertEquals(south.toString(), s.getLiberties().get(1).toString());
    }

    @org.junit.jupiter.api.Test
    void getLiberties4() {
        Stone s = new Stone(1, 1, Color.NOIR);
        West w = new West(true);
        assertEquals(w.toString(), s.getLiberties().get(3).toString());
    }

    @org.junit.jupiter.api.Test
    void getLiberties5() {
        Stone s = new Stone(1, 1, Color.NOIR);
        East e = new East(true);
        assertEquals(e.toString(), s.getLiberties().get(2).toString());
    }

    @org.junit.jupiter.api.Test
    void getLiberties1() {
        Stone s = new Stone(1, 1, Color.NOIR);
        ArrayList<Liberties> liberties = new ArrayList<>();
        liberties.add(new North(true));
        liberties.add(new South(true));
        liberties.add(new East(true));
        liberties.add(new West(true));
        assertEquals(liberties.toString(), s.getLiberties().toString());
    }

    @org.junit.jupiter.api.Test
    void getLiberties2() {
        Stone s = new Stone(1, 1, Color.BLANC);
        ArrayList<Liberties> liberties = new ArrayList<>();
        liberties.add(new North(false));
        liberties.add(new South(false));
        liberties.add(new East(false));
        liberties.add(new West(false));
        assertNotEquals(liberties.toString(), s.getLiberties().toString());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Stone stone = new Stone(1, 1, Color.NOIR);
        North n = new North(true);
        South s = new South(true);
        East e = new East(true);
        West w = new West(true);

        assertEquals("Position (1,1) Couleur = NOIR Liberties: West = true East = true " +
                "South = true North = true", stone.toString());

    }
}