package go.liberty;

import go.Player;
import go.stone.Stone;
import go.stone.StoneGroup;

import java.util.ArrayList;

public class East implements Liberties {
    private boolean free;

    public East(boolean free) {
        this.free = free;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void updateLiberties(Stone stone, ArrayList<Player> players, boolean isAdded) {
        if(stone.getPosition().getX() == upperBound){
            free = false;
        }
        for (Player player : players) {
            for (StoneGroup group : player.getStoneGroup()) {
                for (Stone stoneOld : group.getStoneGroup()) {
                    if (stone.getPosition().getX() == stoneOld.getPosition().getX() - 1 &&
                            stone.getPosition().getY() == stoneOld.getPosition().getY() || stone.getPosition().getX() == upperBound) {
                        stone.getLiberties().set(2, new East(!isAdded));
                        if (stone.getPosition().getX() != upperBound) {
                            stoneOld.getLiberties().set(3, new West(!isAdded));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "E(" + free + ")";
    }
}
