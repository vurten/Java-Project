package Test.board;

import go.board.state.StateEnum;
import go.board.state.WhiteState;

import static org.junit.jupiter.api.Assertions.*;

class WhiteStateTest {

    @org.junit.jupiter.api.Test
    void getState() {
        WhiteState w = new WhiteState();
        assertEquals(w.getState(), StateEnum.fromValue("B"));
    }

    @org.junit.jupiter.api.Test
    void getState1() {
        WhiteState w = new WhiteState();
        assertNotEquals(w.getState(), StateEnum.fromValue("N"));
    }

}