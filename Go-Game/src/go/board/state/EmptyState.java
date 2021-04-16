package go.board.state;

public class EmptyState implements State {
    @Override
    public StateEnum getState() {
        return StateEnum.V;
    }
}
