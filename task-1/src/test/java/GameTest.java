import org.junit.Assert;
import org.junit.Test;
import task1.Game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class GameTest {

    @Test
    public void wrongLengthTest() {
        String input = "1234123";
        String inputExpected = "Эта игра Быки и Коровы. Компьютер загадал число из 4 неповторяющихся цифер. Успехов! Отгадывайте: \r\n" +
                "Не правильная длина!\r\n" +
                "Отгадывайте:";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        InputStream originalInputStream = System.in;
        System.setIn(byteArrayInputStream);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Game game = new Game();
        try {
            game.startGame();
        } catch (Exception e) {
            String output = outputStream.toString().trim();
            Assert.assertEquals(inputExpected, output);
        }
        System.setOut(originalOut);
        System.setIn(originalInputStream);
    }

    @Test
    public void rightNumberTest() {
        String inputExpected = "Эта игра Быки и Коровы. Компьютер загадал число из 4 неповторяющихся цифер. Успехов! Отгадывайте: \r\n" +
                "Успех!";
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Game game = new Game();

        String input = game.getRightAnswer();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        InputStream originalInputStream = System.in;
        System.setIn(byteArrayInputStream);

        try {
            game.startGame();
        } catch (Exception e) {
            String output = outputStream.toString().trim();
            Assert.assertEquals(inputExpected, output);
        }
        System.setOut(originalOut);
        System.setIn(originalInputStream);
    }

    @Test
    public void countBullsAndCowsTest() {
        String inputExpected = "Эта игра Быки и Коровы. Компьютер загадал число из 4 неповторяющихся цифер. Успехов! Отгадывайте: \r\n" +
                "3 Коров 3 Быков\r\n" +
                "Отгадывайте:";
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Game game = new Game();
        String ans = game.getRightAnswer();
        String test = ans.substring(0, 3);
        String symPool = "0123456789";
        for (int i = 0; i < symPool.length(); i++) {
            if (!(test + symPool.charAt(i)).equals(ans)) {
                test = test + symPool.charAt(i);
                break;
            }
        }
        String input = test;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        InputStream originalInputStream = System.in;
        System.setIn(byteArrayInputStream);

        try {
            game.startGame();
        } catch (Exception e) {
            String output = outputStream.toString().trim();
            Assert.assertEquals(inputExpected, output);
        }
        System.setOut(originalOut);
        System.setIn(originalInputStream);
    }
}