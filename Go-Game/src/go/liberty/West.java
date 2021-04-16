package go.liberty;

import go.Player;
import go.stone.Stone;
import go.stone.StoneGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class West implements Liberties, Serializable {
    private boolean free;

    public West(boolean free) {
        this.free = free;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void updateLiberties(Stone stone, ArrayList<Player> players, boolean isAdded) {
        if(stone.getPosition().getX() == lowerBound){
            free = false;
        }
        for (Player player : players) {
            for (StoneGroup group : player.getStoneGroup()) {
                for (Stone stoneOld : group.getStoneGroup()) {
                    if (stone.getPosition().getX() == stoneOld.getPosition().getX() + 1 &&
                            stone.getPosition().getY() == stoneOld.getPosition().getY() || stone.getPosition().getX() == lowerBound) {
                        stone.getLiberties().set(3, new West(!isAdded));
                        if (stone.getPosition().getX() != lowerBound) {
                            stoneOld.getLiberties().set(2, new East(!isAdded));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "W(" + free + ")";
    }
}
