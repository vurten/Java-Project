package Test;

import go.Controller;
import go.Player;
import go.board.Board;
import go.stone.Color;
import go.stone.Stone;
import mock.DisplayerMock;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void getConsecutivePass() {
        ArrayList<Stone> stonesToPlay = new ArrayList<>();
        stonesToPlay.add(new Stone(2,1, Color.NOIR));
        stonesToPlay.add(new Stone(3,1, Color.BLANC));
        stonesToPlay.add(new Stone(0,0, Color.NOIR));
        stonesToPlay.add(new Stone(0,0, Color.BLANC));
        Controller c = new Controller(stonesToPlay);
        c.startGame();
        try{
            for(int i = 0 ; i < stonesToPlay.size(); i++){
                c.playStone(i);
            }
        }catch (Exception e){

        }

        assertEquals(2, c.getConsecutivePass());
    }

    @Test
    void getStonesToPlay() {
        ArrayList<Stone> stonesToPlay = new ArrayList<>();
        stonesToPlay.add(new Stone(1,1, Color.NOIR));
        stonesToPlay.add(new Stone(2,2, Color.NOIR));
        stonesToPlay.add(new Stone(0,0, Color.NOIR));
        Controller c = new Controller(stonesToPlay);
        ArrayList<Stone> stonesToPlay1 = new ArrayList<>();
        stonesToPlay1.add(new Stone(1,1, Color.NOIR));
        stonesToPlay1.add(new Stone(2,2, Color.NOIR));
        stonesToPlay1.add(new Stone(0,0, Color.NOIR));
        Assert.assertTrue(Collections.disjoint(stonesToPlay1, c.getStonesToPlay()));
    }

    @Test
    void getBoard() {
        ArrayList<Stone> stonesToPlay = new ArrayList<>();
        stonesToPlay.add(new Stone(1,1, Color.NOIR));
        stonesToPlay.add(new Stone(2,2, Color.NOIR));
        stonesToPlay.add(new Stone(0,0, Color.NOIR));
        Controller c = new Controller(stonesToPlay);
        Board board = new Board();
        assertNotEquals(board, c.getBoard());

    }

    @Test
    void getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        ArrayList<Player> players1 = new ArrayList<>();
        players1.add(new Player());
        players1.add(new Player());
        ArrayList<Stone> stonesToPlay = new ArrayList<>();
        stonesToPlay.add(new Stone(1,1, Color.NOIR));
        stonesToPlay.add(new Stone(2,2, Color.NOIR));
        stonesToPlay.add(new Stone(0,0, Color.NOIR));
        Assert.assertTrue(Collections.disjoint(players, players1));
    }

    @Test
    void calculatePoints(){
        DisplayerMock d = new DisplayerMock("test5.txt");
        d.start();
        assertEquals(36,d.getController().getPlayers().get(1).getPoints());
    }
}