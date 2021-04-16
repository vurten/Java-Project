package Test.board;

import go.board.state.EmptyState;
import go.board.state.StateEnum;

import static org.junit.jupiter.api.Assertions.*;

class EmptyStateTest {

    @org.junit.jupiter.api.Test
    void getState() {
        EmptyState e = new EmptyState();
        assertEquals(e.getState(), StateEnum.fromValue("V"));
    }

    @org.junit.jupiter.api.Test
    void getState1() {
        EmptyState e = new EmptyState();
        assertNotEquals(e.getState(), StateEnum.fromValue("N"));
    }
}