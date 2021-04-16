package go.stone;

import go.liberty.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoneGroup implements Serializable {
    private List<Stone> stoneGroup = new ArrayList<>();
    private List<Liberties> libertyList = new ArrayList<>();

    public boolean isCaptured() {
        boolean capturedNorth, capturedSouth, capturedEast, capturedWest;

        capturedNorth = !(libertyList.get(0).isFree());
        capturedSouth = !(libertyList.get(1).isFree());
        capturedEast = !(libertyList.get(2).isFree());
        capturedWest = !(libertyList.get(3).isFree());

        return capturedNorth & capturedSouth & capturedEast & capturedWest;
    }

    public boolean isFromGroup(Stone stone) {
        for (Stone fromStoneGroup : stoneGroup) {
            if (isNorth(stone, fromStoneGroup)) {
                return true;
            } else if (isSouth(stone, fromStoneGroup)) {
                return true;
            } else if (isWest(stone, fromStoneGroup)) {
                return true;
            } else if (isEast(stone, fromStoneGroup)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEast(Stone stone, Stone fromStoneGroup) {
        return (stone.getPosition().getX() == fromStoneGroup.getPosition().getX() + 1) && (stone.getPosition().getY() == fromStoneGroup.getPosition().getY());
    }

    private boolean isWest(Stone stone, Stone fromStoneGroup) {
        return (stone.getPosition().getX() == fromStoneGroup.getPosition().getX() - 1) && (stone.getPosition().getY() == fromStoneGroup.getPosition().getY());
    }

    private boolean isSouth(Stone stone, Stone fromStoneGroup) {
        return (stone.getPosition().getX() == fromStoneGroup.getPosition().getX()) && (stone.getPosition().getY() == fromStoneGroup.getPosition().getY() - 1);
    }

    private boolean isNorth(Stone stone, Stone fromStoneGroup) {
        return (stone.getPosition().getX() == fromStoneGroup.getPosition().getX()) && (stone.getPosition().getY() == fromStoneGroup.getPosition().getY() + 1);
    }

    public void updateGroupLiberties() {
        int northTrues = 0, southTrues = 0, eastTrues = 0, westTrues = 0;

        for (Stone fromStoneGroup : stoneGroup) {
            if (fromStoneGroup.getLiberties().get(0).isFree()) {
                northTrues = ++northTrues;
            }
            if (fromStoneGroup.getLiberties().get(1).isFree()) {
                southTrues = ++southTrues;
            }
            if (fromStoneGroup.getLiberties().get(2).isFree()) {
                eastTrues = ++eastTrues;
            }
            if (fromStoneGroup.getLiberties().get(3).isFree()) {
                westTrues = ++westTrues;
            }
        }
        if (northTrues > 0) {
            libertyList.set(0, new North(true));
        } else {
            libertyList.set(0, new North(false));
        }

        if (southTrues > 0) {
            libertyList.set(1, new South(true));
        } else {
            libertyList.set(1, new South(false));
        }

        if (eastTrues > 0) {
            libertyList.set(2, new East(true));
        } else {
            libertyList.set(2, new East(false));
        }

        if (westTrues > 0) {
            libertyList.set(3, new West(true));
        } else {
            libertyList.set(3, new West(false));
        }
    }

    public void initGroupLiberties(List<Liberties> liberties) {
        libertyList.add(new North(liberties.get(0).isFree()));
        libertyList.add(new South(liberties.get(1).isFree()));
        libertyList.add(new East(liberties.get(2).isFree()));
        libertyList.add(new West(liberties.get(3).isFree()));
    }

    public List<Stone> getStoneGroup() {
        return stoneGroup;
    }
}
