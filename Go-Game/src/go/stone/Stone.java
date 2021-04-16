package go.stone;

import go.liberty.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Stone implements Serializable {
    private Color color;
    private Position position = new Position();
    private ArrayList<Liberties> liberties = new ArrayList<>();

    public Stone(int x, int y, Color color) {
        position.setX(x);
        position.setY(y);
        this.color = color;
        liberties.add(new North(true));
        liberties.add(new South(true));
        liberties.add(new East(true));
        liberties.add(new West(true));
    }


    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Liberties> getLiberties() {
        return liberties;
    }

    @Override
    public String toString() {
        return "Position " + position + " Couleur = " + color + " Liberties: West = "
                + liberties.get(3).isFree() + " East = " + liberties.get(2).isFree()
                + " South = " + liberties.get(1).isFree() + " North = " + liberties.get(0).isFree();
    }
}
