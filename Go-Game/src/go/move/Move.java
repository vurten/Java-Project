package go.move;

import go.Player;
import go.board.Board;
import go.stone.Stone;
import go.stone.StoneGroup;

import java.util.ArrayList;
import java.util.Collections;

public class Move {

    private Player attacker;
    private Player attackee;
    private int index;
    private ArrayList<Stone> stonesToPlay;

    private Player tempAttacker;
    private Player tempAttackee;
    private ArrayList<Integer> kill = new ArrayList<>();

    public Move(Player attacker, Player attackee, int index, ArrayList<Stone> stonesToPlay) {
        this.attacker = attacker;
        this.attackee = attackee;
        this.index = index;
        this.stonesToPlay = stonesToPlay;
        initTempPlayers();
    }

    public void initTempPlayers() {
        tempAttacker = attacker.getCopy();
        tempAttackee = attackee.getCopy();
        tempAttacker.addStone(stonesToPlay.get(index));
        tempAttacker.updateGroupsLiberties();
        tempAttackee.updateGroupsLiberties();
        kill();
    }

    public boolean verifyLastMove() {
        ArrayList<Player> tempPlayers = new ArrayList<>();
        tempPlayers.add(tempAttacker);
        tempPlayers.add(tempAttackee);

        for (int killIndex : kill) {
            tempAttackee.removeStone(killIndex, tempPlayers);
        }

        Board tempBoard = new Board();
        tempBoard.updateState(tempPlayers);

        return tempAttacker.getLastBoard().equals(tempBoard.generateBoard());
    }

    public boolean verifyOccupiedSpace() {
        return occupiedSpace(attacker, stonesToPlay, index) || occupiedSpace(attackee, stonesToPlay, index);
    }

    public boolean verifySuicide() {
        return suicide(tempAttacker);
    }

    public ArrayList<Integer> verifyKill() {
        return kill;
    }

    private boolean occupiedSpace(Player player, ArrayList<Stone> stonesToPlay, int index) {
        for (StoneGroup fromPlayer : player.getStoneGroup()) {
            for (Stone fromStoneGroup : fromPlayer.getStoneGroup()) {
                if (stonesToPlay.get(index).getPosition().equals(fromStoneGroup.getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Integer> kill() {
        kill = new ArrayList<>();

        for (int i = 0; i < tempAttackee.getStoneGroup().size(); i++) {
            if (tempAttackee.getStoneGroup().get(i).isCaptured()) {
                kill.add(i);
            }
        }
        Collections.reverse(kill);

        return kill;
    }

    private boolean suicide(Player tempAttacker) {
        boolean suicide = false;

        for (int i = 0; i < tempAttacker.getStoneGroup().size(); i++) {
            if (tempAttacker.getStoneGroup().get(i).isCaptured()) {
                suicide = true;
            }
        }

        return suicide;
    }
}
