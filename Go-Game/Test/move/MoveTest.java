package Test.move;

import go.Controller;
import go.Player;
import go.move.Move;
import go.stone.Color;
import go.stone.Stone;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @org.junit.jupiter.api.Test
    void verifyLastMove() {
        Player attacker = new Player();
        Player attackee = new Player();
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        assertFalse(m.verifyLastMove());
    }

    @org.junit.jupiter.api.Test
    void testKo() {
        int index = 8;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(5, 5, Color.NOIR));
        stonesToPlay.add(new Stone(6, 5, Color.BLANC));
        stonesToPlay.add(new Stone(5, 7, Color.NOIR));
        stonesToPlay.add(new Stone(6, 7, Color.BLANC));
        stonesToPlay.add(new Stone(6, 6, Color.NOIR));
        stonesToPlay.add(new Stone(7, 6, Color.BLANC));
        stonesToPlay.add(new Stone(4, 6, Color.NOIR));
        stonesToPlay.add(new Stone(5, 6, Color.BLANC));
        Controller c = new Controller(stonesToPlay);
        c.startGame();
        for(int i = 0; i<stonesToPlay.size();i++){
            try {
                c.playStone(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print(c.getBoard().generateBoard());
        stonesToPlay.add(new Stone(6, 6, Color.NOIR));
        for (int j = 0; j < 4; j++) {
            stonesToPlay.get(index).getLiberties().get(j).updateLiberties(stonesToPlay.get(index), c.getPlayers(), true);
        }
        Move m = new Move(c.getPlayers().get(0), c.getPlayers().get(1), index, stonesToPlay);
        assertTrue(m.verifyLastMove());
    }

    @org.junit.jupiter.api.Test
    void verifyOccupiedSpace() {
        Player attacker = new Player();
        Player attackee = new Player();
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        assertFalse(m.verifyOccupiedSpace());
    }

    @org.junit.jupiter.api.Test
    void verifyOccupiedSpace1() {
        Player attacker = new Player();
        Player attackee = new Player();
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        attackee.addStone(new Stone(1, 1, Color.NOIR));
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        assertTrue(m.verifyOccupiedSpace());
    }

    @org.junit.jupiter.api.Test
    void verifySuicide() {
        Player attacker = new Player();
        Player attackee = new Player();
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(attackee);
        players.add(attacker);
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        attackee.addStone(new Stone(1, 2, Color.NOIR));
        attackee.addStone(new Stone(2, 1, Color.NOIR));
        for (int j = 0; j < 4; j++) {
            stonesToPlay.get(index).getLiberties().get(j).updateLiberties(stonesToPlay.get(index), players, true);
        }
        attackee.updateGroupsLiberties();
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        assertTrue(m.verifySuicide());
    }

    @org.junit.jupiter.api.Test
    void verifyKill() {

        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        stonesToPlay.add(new Stone(2, 1, Color.BLANC));
        stonesToPlay.add(new Stone(5, 5, Color.NOIR));
        stonesToPlay.add(new Stone(1, 2, Color.BLANC));
        Controller controller = new Controller(stonesToPlay);
        controller.startGame();
        try{
            for(int i = 0; i < stonesToPlay.size()-1; i++){
                controller.playStone(i);
            }
        }catch (Exception e){

        }
        for (int j = 0; j < 4; j++) {
            stonesToPlay.get(3).getLiberties().get(j).updateLiberties(stonesToPlay.get(3), controller.getPlayers(), true);
        }
        Move move = new Move(controller.getPlayers().get(1), controller.getPlayers().get(0), 3, stonesToPlay);
        assertEquals(1, move.verifyKill().size());
    }

    @org.junit.jupiter.api.Test
    void noKill() {
        Player attacker = new Player();
        Player attackee = new Player();
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        assertEquals(0, m.verifyKill().size());
    }

    @org.junit.jupiter.api.Test
    void noSuicide() {
        Player attacker = new Player();
        Player attackee = new Player();
        int index = 0;
        ArrayList<Stone> stonesToPlay = new ArrayList<Stone>();
        stonesToPlay.add(new Stone(1, 1, Color.NOIR));
        Move m = new Move(attacker, attackee, index, stonesToPlay);
        attackee.addStone(new Stone(1, 2, Color.NOIR));
        assertFalse(m.verifySuicide());
    }
}