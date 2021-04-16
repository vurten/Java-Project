package go.move;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(String message) {
        super("Mouvement invalide: " + message + " Veuillez jouer à nouveau.");
    }
}
