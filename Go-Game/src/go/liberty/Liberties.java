package go.liberty;


import go.Player;
import go.stone.Stone;

import java.io.Serializable;
import java.util.ArrayList;

public interface Liberties extends Serializable {
    boolean isFree();

    void updateLiberties(Stone stone, ArrayList<Player> players, boolean isAdded);

    int upperBound = 9;
    int lowerBound = 1;
}
