package go.liberty;

import go.Player;
import go.stone.Stone;
import go.stone.StoneGroup;

import java.util.ArrayList;

public class North implements Liberties {

    private boolean free;

    public North(boolean free) {
        this.free = free;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void updateLiberties(Stone stone, ArrayList<Player> players, boolean isAdded) {
        if(stone.getPosition().getY() == upperBound){
            free = false;
        }
        for (Player player : players) {
            for (StoneGroup group : player.getStoneGroup()) {
                for (Stone stoneOld : group.getStoneGroup()) {
                    if (stone.getPosition().getY() == stoneOld.getPosition().getY() - 1 &&
                            stone.getPosition().getX() == stoneOld.getPosition().getX() || stone.getPosition().getY() == upperBound) {
                        stone.getLiberties().set(0, new North(!isAdded));
                        if (stone.getPosition().getY() != upperBound) {
                            stoneOld.getLiberties().set(1, new South(!isAdded));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "N(" + free + ")";
    }
}
