import org.junit.Assert;
import org.junit.Test;
import task2.InputParser;

public class InputParserTest {

    @Test
    public void getCommandNameTest() {
        InputParser inputParser = new InputParser();
        String str = "PUSH 1";
        String commandName = inputParser.getCommandName(str);
        Assert.assertEquals("PUSH", commandName);
    }

    @Test
    public void getEmptyCommandNameTest() {
        InputParser inputParser = new InputParser();
        String str = "";
        String commandName = inputParser.getCommandName(str);
        Assert.assertNull(commandName);
    }

    @Test
    public void getCommentCommandNameTest() {
        InputParser inputParser = new InputParser();
        String str = "# ночь коротка цель далека";
        String commandName = inputParser.getCommandName(str);
        Assert.assertNull(commandName);
    }

    @Test
    public void getCommandAttributesTest() {
        InputParser inputParser = new InputParser();
        String str = "PUSH 1";
        String[] attributes = inputParser.getAttributes(str);
        String[] correctAttributes = {"1"};
        Assert.assertArrayEquals(attributes, correctAttributes);
    }

    @Test
    public void getEmptyCommandAttributesTest() {
        InputParser inputParser = new InputParser();
        String str = "SQRT";
        String[] attributes = inputParser.getAttributes(str);
        String[] correctAttributes = {};
        Assert.assertArrayEquals(attributes, correctAttributes);
    }

    @Test
    public void getManyCommandAttributesTest() {
        InputParser inputParser = new InputParser();
        String str = "PUSH A B C AB ABC ";
        String[] attributes = inputParser.getAttributes(str);
        String[] correctAttributes = {"A", "B", "C", "AB", "ABC"};
        Assert.assertArrayEquals(attributes, correctAttributes);
    }
}