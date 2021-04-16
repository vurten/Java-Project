package go;

import go.stone.Stone;
import go.stone.StoneGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Serializable {

    private String lastBoard = "";
    private List<StoneGroup> stoneGroup = new ArrayList<>();
    private int points = 0;

    public Player() {
    }

    public List<StoneGroup> getStoneGroup() {
        return stoneGroup;
    }

    public void addStone(Stone stone) {
        boolean addedToGroup = false;
        ArrayList<Integer> groupPositions = new ArrayList<>();
        for (int i = 0; i < stoneGroup.size(); i++) {
            if (stoneGroup.get(i).isFromGroup(stone)) {
                groupPositions.add(i);
                addedToGroup = true;
            }
        }

        if (!addedToGroup) {
            StoneGroup newGroup = new StoneGroup();
            newGroup.getStoneGroup().add(stone);
            stoneGroup.add(newGroup);
            newGroup.initGroupLiberties(stone.getLiberties());
        } else {
            StoneGroup newGroup = new StoneGroup();
            for (int i : groupPositions) {
                for (Stone tempStone : stoneGroup.get(i).getStoneGroup()) {
                    newGroup.getStoneGroup().add(tempStone);
                }
            }
            Collections.reverse(groupPositions);
            for (int i : groupPositions) {
                stoneGroup.remove(i);
            }
            newGroup.getStoneGroup().add(stone);
            stoneGroup.add(newGroup);
            newGroup.initGroupLiberties(stone.getLiberties());
        }
    }

    public void updateGroupsLiberties() {
        for (StoneGroup groupFromPlayer : stoneGroup) {
            groupFromPlayer.updateGroupLiberties();
        }
    }

    public void removeStone(int groupIndex, ArrayList<Player> players) {
        for (int i = stoneGroup.get(groupIndex).getStoneGroup().size() - 1; i >= 0; i--) {
            Stone stone = stoneGroup.get(groupIndex).getStoneGroup().get(i);
            for (int j = 0; j < 4; j++) {
                stone.getLiberties().get(j).updateLiberties(stone, players, false);
            }
            stoneGroup.get(groupIndex).getStoneGroup().remove(i);
        }
        stoneGroup.remove(groupIndex);
    }

    public Player getCopy() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Player) ois.readObject();
        } catch (Exception e) {
            System.out.println("ERROR COPY " + e.getMessage());
            return null;
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLastBoard() {
        return lastBoard;
    }

    public void setLastBoard(String lastBoard) {
        this.lastBoard = lastBoard;
    }
}