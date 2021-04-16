package Test.board;

import go.board.state.BlackState;
import go.board.state.StateEnum;

import static org.junit.jupiter.api.Assertions.*;

class BlackStateTest {

    @org.junit.jupiter.api.Test
    void getState() {
        BlackState b = new BlackState();
        assertEquals(b.getState(), StateEnum.fromValue("N"));

    }

    @org.junit.jupiter.api.Test
    void getState1() {
        BlackState b = new BlackState();
        assertNotEquals(b.getState(), StateEnum.fromValue("V"));
    }
}