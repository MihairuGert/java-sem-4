import org.junit.Assert;
import org.junit.Test;
import task2.Command;
import task2.CommandFactory;

public class CommandFactoryTest {

    @Test
    public void newCommandTest() {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.newCommand("PUSH");
        try {
            Assert.assertEquals(Class.forName("task2.commands.PushCommand"), command.getClass());
        } catch (ClassNotFoundException e) {
            return;
        }
        command = commandFactory.newCommand("POP");
        try {
            Assert.assertEquals(Class.forName("task2.commands.PopCommand"), command.getClass());
        } catch (ClassNotFoundException e) {
            return;
        }
    }

    @Test
    public void newNotExistCommandTest() {
        CommandFactory commandFactory = new CommandFactory();
        try {
            Command command = commandFactory.newCommand("DOES_NOT_EXIST");
        } catch (Exception e) {
            Assert.assertEquals("Command with such name does not exist.", e.getMessage());
        }
    }


}
