package Test.board;

import go.board.state.StateEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateTest {

    @org.junit.jupiter.api.Test
    void fromValueV() {
        assertEquals(StateEnum.V, StateEnum.fromValue("V"));
    }

    @org.junit.jupiter.api.Test
    void fromValueB() {
        assertEquals(StateEnum.B, StateEnum.fromValue("B"));
    }

    @org.junit.jupiter.api.Test
    void fromValueW() {
        assertEquals(StateEnum.N, StateEnum.fromValue("N"));
    }
}