package go;

import go.stone.Alphabet;
import go.stone.Color;
import go.stone.Stone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Displayer {
    private ArrayList<Stone> stonesToPlay = new ArrayList<>();
    private String fileName = "";
    private Controller controller;

    public Displayer(){ }

    public Displayer(String fileName) {
        this.fileName = fileName;
    }

    public void start() {
        try {
            stonesToPlay = readFile();
        } catch (Exception e) {
            System.out.println("Le fichier de partie est inexistant, une nouvelle partie débute. \n");
        }
        controller = new Controller(stonesToPlay);
        controller.startGame();
        int i = 0;
        while (controller.getConsecutivePass() != 2) {
            if (i >= controller.getStonesToPlay().size()) {
                addNewStone();
            }
            printTurn(stonesToPlay.get(i).getColor().getColor(), i);

            try {
                controller.playStone(i);
                printBoard();
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                stonesToPlay.remove(stonesToPlay.size() - 1);
            }
        }
        controller.calculatePoints();
        printScore();
    }

    private void printTurn(String color, int i) {
        System.out.print(String.format("\n     Tour #%d", (i + 1)));
        if (stonesToPlay.get(i).getPosition().getX() == 0 && stonesToPlay.get(i).getPosition().getY() == 0) {
            System.out.println(String.format(" - Joueur %s PASS", color));
        } else {
            System.out.println(String.format(" - Joueur %s", color));
        }
    }

    private ArrayList<Stone> startingStoneList(String stoneInString) {

        ArrayList<Stone> listOfStone = new ArrayList<>();

        String[] stones = stoneInString.trim().split(" ");

        for (int i = 0; i < stones.length; i++) {
            Color color;
            if (i % 2 == 0) {
                color = Color.NOIR;
            } else {
                color = Color.BLANC;
            }
            if (stones[i].equalsIgnoreCase("PASS")) {
                listOfStone.add(new Stone(0, 0, color));
            } else {
                int y = Integer.parseInt(stones[i].substring(1));
                listOfStone.add(new Stone(Alphabet.valueOf(stones[i].substring(0, 1)).getEnumValue(), y, color));
            }
        }
        return listOfStone;
    }

    private ArrayList<Stone> readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        String stoneString = scanner.nextLine();
        scanner.close();
        return startingStoneList(stoneString);
    }

    public void printBoard() {
        System.out.println(controller.getBoard().generateBoard());
    }

    public void addNewStone() {
        Color color;
        if (stonesToPlay.size() % 2 == 0) {
            color = Color.NOIR;
            System.out.println("Le tour du Joueur Noir");
        } else {
            color = Color.BLANC;
            System.out.println("Le tour du Joueur Blanc");
        }
        Scanner sc = new Scanner(System.in);
        String newStone = sc.nextLine();
        newStone = newStone.toUpperCase();
        if (newStone.equalsIgnoreCase("PASS")) {
            stonesToPlay.add(new Stone(0, 0, color));
        } else {
            int y = Integer.parseInt(newStone.substring(1));
            String x = newStone.substring(0, 1);
            if(isInside(y, x)){
                stonesToPlay.add(new Stone(Alphabet.valueOf(newStone.substring(0, 1)).getEnumValue(), y, color));
            }else{
                System.out.println("Emplacement invalide, veuillez jouer à nouveau.");
                addNewStone();
            }
        }

    }

    private boolean isInside(int y, String x) {
        return y > 0 && y < 10 && x.matches("[A-HJ]");
    }

    public void printScore() {
        System.out.println("Partie terminée.");
        System.out.println("Point Joueur Noir: " + controller.getPlayers().get(0).getPoints() + ".");
        System.out.println("Point Joueur Blanc: " + controller.getPlayers().get(1).getPoints() + ".");
        if (controller.getPlayers().get(0).getPoints() > controller.getPlayers().get(1).getPoints()) {
            System.out.println("Le Joueur Noir est le vainqueur.");
        } else if (controller.getPlayers().get(1).getPoints() > controller.getPlayers().get(0).getPoints()) {
            System.out.println("Le Joueur Blanc est le vainqueur.");
        } else {
            System.out.println("Les deux Joueurs ont le même nombre de points, la partie est nulle.");
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
