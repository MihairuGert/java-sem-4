import org.junit.Assert;
import org.junit.Test;
import task2.Context;
import task2.commands.binaryOps.BinaryOpCommand;

public class BinaryOpCommandTest {

    @Test
    public void NotEnoughElementsTest() {
        Context context = new Context();
        BinaryOpCommand binaryOpCommand = new BinaryOpCommand();
        try {
            binaryOpCommand.execute(context, new String[] {});
        } catch (Exception e) {
            Assert.assertEquals("Not enough elements to perform operation.", e.getMessage());
        }
        context.pushOnStack(1);
        try {
            binaryOpCommand.execute(context, new String[] {});
        } catch (Exception e) {
            Assert.assertEquals("Not enough elements to perform operation.", e.getMessage());
        }
    }
}
