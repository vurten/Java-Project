package go.board;

import go.Player;
import go.board.state.BlackState;
import go.board.state.EmptyState;
import go.board.state.StateEnum;
import go.board.state.WhiteState;
import go.stone.Color;
import go.stone.Stone;
import go.stone.StoneGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int boardSize = 9;
    private String[][] board = new String[boardSize + 1][boardSize + 1];
    private StateEnum[][] boardState = new StateEnum[boardSize][boardSize];
    private WhiteState white = new WhiteState();
    private BlackState black = new BlackState();
    private EmptyState empty = new EmptyState();
    private final static List<String> columns = new ArrayList<String>(
            Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "J")
    );

    public Board() {
        initBoard();
    }

    public void updateState(List<Player> players) {
        initState();
        for (Player player : players) {
            for (StoneGroup group : player.getStoneGroup()) {
                for (Stone stone : group.getStoneGroup()) {
                    if (stone.getColor() == Color.NOIR) {
                        boardState[(Math.abs(stone.getPosition().getY() - boardSize))][stone.getPosition().getX() - 1] = black.getState();
                    } else {
                        boardState[(Math.abs(stone.getPosition().getY() - boardSize))][stone.getPosition().getX() - 1] = white.getState();
                    }
                }
            }
        }
    }

    private void initState() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boardState[i][j] = empty.getState();
            }
        }
    }

    private void initBoard() {
        initState();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = "  ";
                } else if (i == 0) {
                    board[i][j] = " " + columns.get(j - 1) + " ";
                } else if (j == 0) {
                    board[i][j] = (Math.abs(i - 10)) + " ";
                } else {
                    board[i][j] = empty.getState().state;
                }
            }
        }
    }

    public String generateBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i + 1][j + 1] = boardState[i][j].state;
            }
        }

        String boardRepresentation = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardRepresentation += board[i][j];
            }
            boardRepresentation += "\n";
        }
        return boardRepresentation;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public StateEnum getState(int x, int y) {
        return boardState[y][x];
    }
}
