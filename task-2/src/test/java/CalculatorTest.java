import org.junit.Assert;
import org.junit.Test;
import task2.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CalculatorTest {

    @Test
    public void runCalculatorTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Scanner scanner = new Scanner("DEFINE a 4\n" +
                "PUSH a\n" +
                "SQRT\n" +
                "PRINT");
        scanner.useDelimiter("\n");

        Calculator calculator = new Calculator();
        calculator.runCalculator(scanner);

        String output = outputStream.toString().trim();
        Assert.assertEquals("2.0", output);

        System.setOut(originalOut);
    }

    @Test
    public void runCalculatorLargeTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Scanner scanner = new Scanner("DEFINE a 4\n" +
                "PUSH a\n" +
                "PUSH a\n" +
                "PUSH a\n" +
                "PUSH a\n" +
                "PUSH a\n" +
                "PUSH 123\n" +
                "POP b\n" +
                "*\n" +
                "/\n" +
                "+\n" +
                "/\n" +
                "DEFINE var 4\n" +
                "PUSH var\n" +
                "-\n" +
                "PRINT\n" +
                "PUSH b\n" +
                "PRINT\n" +
                "POP\n" +
                "PRINT");
        scanner.useDelimiter("\n");

        Calculator calculator = new Calculator();
        calculator.runCalculator(scanner);

        String output = outputStream.toString().trim();
        Assert.assertEquals("2.0\r\n123.0\r\n2.0", output);

        System.setOut(originalOut);
    }
}
