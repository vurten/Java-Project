import mock.DisplayerMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DisplayerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printBoard(){
        DisplayerMock d = new DisplayerMock("test7.txt");
        d.start();
        String s = "A  B  C  D  E  F  G  H  J \n" +
                "9  .  .  .  .  .  .  .  .  . \n" +
                "8  .  .  .  .  .  .  .  .  . \n" +
                "7  .  .  .  .  .  .  .  .  . \n" +
                "6  .  .  .  B  .  .  .  .  . \n" +
                "5  .  .  .  .  N  .  .  .  . \n" +
                "4  .  .  .  .  .  .  .  .  . \n" +
                "3  .  .  .  .  .  .  .  .  . \n" +
                "2  .  .  .  .  .  .  .  .  . \n" +
                "1  .  .  .  .  .  .  .  .  .";
        d.printBoard();
        assertEquals(s, outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
