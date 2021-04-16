package go.board.state;

public enum StateEnum {
    B(" B "),
    N(" N "),
    V(" . ");

    public String state;

    StateEnum(String state) {
        this.state = state;
    }

    public static StateEnum fromValue(String state) {
        if (state.equals(B.name())) {
            return StateEnum.B;
        } else if (state.equals(N.name())) {
            return StateEnum.N;
        }
        return StateEnum.V;
    }


}
