package go;

import go.stone.Stone;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface DisplayerInterface {
    ArrayList<Stone> startingStoneList(String stoneInString);
    void start();
    ArrayList<Stone> readFile() throws FileNotFoundException;
}
