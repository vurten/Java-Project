package Test;

import go.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getPoints() {
        Player p = new Player();
        p.setPoints(10);
        assertEquals(10, p.getPoints());
    }

    @Test
    void getPoints1() {
        Player p = new Player();
        p.setPoints(10);
        assertNotEquals(1, p.getPoints());
    }

    @Test
    void getLastBoard() {
        Player p = new Player();
        p.setLastBoard("test");
        assertEquals("test", p.getLastBoard());
    }

    @Test
    void getLastBoard1() {
        Player p = new Player();
        p.setLastBoard("test1");
        assertNotEquals("test", p.getLastBoard());
    }

}