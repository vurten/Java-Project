package go.stone;

import java.io.Serializable;

public class Position implements Serializable {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position p = (Position) o;

        return p.x == this.x && p.y == this.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
