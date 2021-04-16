package go.board.observer;

import go.Player;
import go.board.Board;

import java.util.List;

public class BoardStateObserver implements Observer {
    private Board board;
    private List<Player> players;

    public BoardStateObserver(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    @Override
    public void update() {
        board.updateState(players);
    }
}
