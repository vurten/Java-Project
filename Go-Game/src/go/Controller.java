package go;

import go.board.Board;
import go.board.observer.BoardStateObserver;
import go.board.observer.Observer;
import go.board.state.StateEnum;
import go.move.ExceptionMessages;
import go.move.InvalidMoveException;
import go.move.Move;
import go.stone.Color;
import go.stone.Stone;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Stone> stonesToPlay;
    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int consecutivePass = 0;
    private boolean blackGroup;
    private boolean whiteGroup;
    private int currentGroup = 0;
    private Observer observer;

    public Controller(ArrayList<Stone> stonesToPlay) {
        this.stonesToPlay = stonesToPlay;
    }

    public void startGame() {
        initBoard();
        initPlayers();
    }

    private void initBoard() {
        board = new Board();
        observer = new BoardStateObserver(board, players);
    }

    private void initPlayers() {
        Player player1 = new Player();
        Player player2 = new Player();
        players.add(player1);
        players.add(player2);
    }

    public void playStone(int turnIndex) throws Exception {
        if (stonesToPlay.get(turnIndex).getPosition().getY() != 0 && stonesToPlay.get(turnIndex).getPosition().getX() != 0) {
            consecutivePass = 0;
            for (int j = 0; j < 4; j++) {
                stonesToPlay.get(turnIndex).getLiberties().get(j).updateLiberties(stonesToPlay.get(turnIndex), players, true);
            }

            if (stonesToPlay.get(turnIndex).getColor() == Color.NOIR) {
                playMove(turnIndex, players.get(0), players.get(1));
            } else {
                playMove(turnIndex, players.get(1), players.get(0));
            }

            players.get(0).updateGroupsLiberties();
            players.get(1).updateGroupsLiberties();
        } else {
            consecutivePass++;
        }
    }

    private void playMove(int turnIndex, Player attacker, Player attackee) throws Exception {
        Move move = new Move(attacker, attackee, turnIndex, stonesToPlay);
        verifyMove(move, turnIndex);
        attacker.addStone(stonesToPlay.get(turnIndex));
        ArrayList<Integer> kills = move.verifyKill();
        if (kills.size() > 0) {
            for (int kill : kills) {
                attacker.setPoints(attacker.getPoints() + attackee.getStoneGroup().get(kill).getStoneGroup().size());
                attackee.removeStone(kill, players);
            }
        }
        observer.update();
        attacker.setLastBoard(board.generateBoard());
    }

    private void verifyMove(Move move, int turnIndex) throws Exception {
        if (move.verifyLastMove()) {
            for (int j = 0; j < 4; j++) {
                stonesToPlay.get(turnIndex).getLiberties().get(j).updateLiberties(stonesToPlay.get(turnIndex), players, false);
            }
            throw new InvalidMoveException(ExceptionMessages.KO_RULE);
        } else if (move.verifyOccupiedSpace()) {
            throw new InvalidMoveException(ExceptionMessages.OCCUPIED_SPACE);
        } else if (move.verifyKill().size() == 0) {
            if (move.verifySuicide()) {
                throw new InvalidMoveException(ExceptionMessages.SUICIDE);
            }
        }
    }

    public void calculatePoints() {
        int pointsBlack = 0;
        int pointsWhite = 0;

        int[][] marks = new int[board.getBoardSize()][board.getBoardSize()];
        currentGroup = 0;
        for (int y = 0; y <= marks.length; y++) {
            for (int x = 0; x <= marks.length; x++) {
                blackGroup = false;
                whiteGroup = false;
                currentGroup++;

                markGroup(y, x, marks);
                int count = 0;

                for (int[] ia : marks) {
                    for (int val : ia) {
                        if (val == currentGroup) {
                            count++;
                        }
                    }
                }
                if (blackGroup && !whiteGroup) {
                    pointsBlack += count;
                } else if (whiteGroup && !blackGroup) {
                    pointsWhite += count;
                }
            }
        }

        players.get(0).setPoints(players.get(0).getPoints() + pointsBlack);
        players.get(1).setPoints(players.get(1).getPoints() + pointsWhite);
    }

    private void markGroup(int y, int x, int[][] mark) {
        if (y < 0 || y >= board.getBoardSize() || x < 0 || x >= board.getBoardSize() || mark[y][x] > 0) {
            return;
        } else if (mark[y][x] == -1) {
            blackGroup = true;
        } else if (mark[y][x] == -2) {
            whiteGroup = true;
        } else if (board.getState(x, y) == StateEnum.N) {
            blackGroup = true;
            mark[y][x] = -1;
        } else if (board.getState(x, y) == StateEnum.B) {
            whiteGroup = true;
            mark[y][x] = -2;
        } else {
            mark[y][x] = currentGroup;
            markGroup(y, x - 1, mark);
            markGroup(y - 1, x, mark);
            markGroup(y, x + 1, mark);
            markGroup(y + 1, x, mark);
        }
    }

    public int getConsecutivePass() {
        return consecutivePass;
    }

    public ArrayList<Stone> getStonesToPlay() {
        return stonesToPlay;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
