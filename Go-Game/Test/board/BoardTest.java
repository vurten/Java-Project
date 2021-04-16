package Test.board;

import go.Player;
import go.board.Board;
import go.board.observer.BoardStateObserver;
import go.board.observer.Observer;
import go.board.state.StateEnum;
import go.stone.Color;
import go.stone.Stone;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private static Board board = new Board();
    private static ArrayList<Player> players = new ArrayList<>();

    @BeforeAll
    static void setup() {
        Player player1 = new Player();
        Player player2 = new Player();
        players.add(player1);
        players.add(player2);
        Observer observer = new BoardStateObserver(board, players);
        player1.addStone(new Stone(3, 3, Color.NOIR));
        player1.addStone(new Stone(4, 3, Color.NOIR));
        player2.addStone(new Stone(4, 5, Color.BLANC));
        player2.addStone(new Stone(4, 4, Color.BLANC));
        observer.update();
    }

    @AfterClass
    static void clean(){
        board = null;
        players = null;
    }

    @org.junit.jupiter.api.Test
    void getBoardSize() {
        assertEquals(9, board.getBoardSize());
    }


    @org.junit.jupiter.api.Test
    void generateBoardVide() {
        Board emptyBoard = new Board();
        assertEquals("   A  B  C  D  E  F  G  H  J \n" +
                "9  .  .  .  .  .  .  .  .  . \n" +
                "8  .  .  .  .  .  .  .  .  . \n" +
                "7  .  .  .  .  .  .  .  .  . \n" +
                "6  .  .  .  .  .  .  .  .  . \n" +
                "5  .  .  .  .  .  .  .  .  . \n" +
                "4  .  .  .  .  .  .  .  .  . \n" +
                "3  .  .  .  .  .  .  .  .  . \n" +
                "2  .  .  .  .  .  .  .  .  . \n" +
                "1  .  .  .  .  .  .  .  .  . \n", emptyBoard.generateBoard());
    }

    @org.junit.jupiter.api.Test
    void generateBoard2() {
        assertEquals("   A  B  C  D  E  F  G  H  J \n" +
                "9  .  .  .  .  .  .  .  .  . \n" +
                "8  .  .  .  .  .  .  .  .  . \n" +
                "7  .  .  .  .  .  .  .  .  . \n" +
                "6  .  .  .  .  .  .  .  .  . \n" +
                "5  .  .  .  B  .  .  .  .  . \n" +
                "4  .  .  .  B  .  .  .  .  . \n" +
                "3  .  .  N  N  .  .  .  .  . \n" +
                "2  .  .  .  .  .  .  .  .  . \n" +
                "1  .  .  .  .  .  .  .  .  . \n", board.generateBoard());
    }

    @org.junit.jupiter.api.Test
    void getStatePoint() {
        assertEquals(StateEnum.V, board.getState(2,2));
        assertEquals(StateEnum.N, board.getState(3, Math.abs(3 - board.getBoardSize())));
        assertEquals(StateEnum.N, board.getState(2, Math.abs(3 - board.getBoardSize())));
        assertEquals(StateEnum.B, board.getState(3, Math.abs(5 - board.getBoardSize())));
        assertEquals(StateEnum.B, board.getState(3, Math.abs(4 - board.getBoardSize())));
    }
}
