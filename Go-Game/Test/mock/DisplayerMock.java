package mock;

import go.Controller;
import go.DisplayerInterface;
import go.stone.Alphabet;
import go.stone.Color;
import go.stone.Stone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayerMock implements DisplayerInterface {
    private ArrayList<Stone> stonesToPlay = new ArrayList<>();
    private String fileName = "";
    private Controller controller;

    @Override
    public ArrayList<Stone> startingStoneList(String stoneInString) {
        ArrayList<Stone> listOfStone = new ArrayList<>();

        String[] stones = stoneInString.trim().split(" ");

        for (int i = 0; i < stones.length; i++) {
            Color color;
            if (i % 2 == 0) {
                color = Color.NOIR;
            } else {
                color = Color.BLANC;
            }
            if (stones[i].equals("PASS")) {
                listOfStone.add(new Stone(0, 0, color));
            } else {
                int y = Integer.parseInt(stones[i].substring(1));
                listOfStone.add(new Stone(Alphabet.valueOf(stones[i].substring(0, 1)).getEnumValue(), y, color));
            }
        }
        return listOfStone;
    }

    @Override
    public void start() {
        try {
            stonesToPlay = readFile();
        } catch (Exception e) {
            System.out.println("Le fichier de partie est inexistant, une nouvelle partie débute. \n");
        }
        controller = new Controller(stonesToPlay);
        controller.startGame();
        int i = 0;
        while (controller.getConsecutivePass() != 2 || i < controller.getStonesToPlay().size()) {
            try {
                controller.playStone(i);
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                stonesToPlay.remove(stonesToPlay.size() - 1);
            }
        }
        controller.calculatePoints();
    }

    @Override
    public ArrayList<Stone> readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        String stoneString = scanner.nextLine();
        scanner.close();
        return startingStoneList(stoneString);
    }

    public Controller getController() {
        return controller;
    }

    public DisplayerMock(String fileName) {
        this.fileName = fileName;
    }

    public void printBoard() {
        System.out.print(controller.getBoard().generateBoard());
    }
}
