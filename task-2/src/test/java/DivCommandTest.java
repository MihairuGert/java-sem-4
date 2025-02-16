import org.junit.Assert;
import org.junit.Test;
import task2.Context;
import task2.commands.binaryOps.DivCommand;

public class DivCommandTest {

    @Test
    public void NotEnoughDivTest() {
        Context context = new Context();
        DivCommand divCommand = new DivCommand();
        context.pushOnStack(1);
        try {
            divCommand.execute(context, new String[] {});
        } catch (Exception e) {
            Assert.assertEquals("Not enough elements to perform division.", e.getMessage());
        }
    }

    @Test
    public void ZeroDivTest() {
        Context context = new Context();
        DivCommand divCommand = new DivCommand();
        context.pushOnStack(0);
        context.pushOnStack(1);
        try {
            divCommand.execute(context, new String[] {});
        } catch (Exception e) {
            try {
                Assert.assertEquals(1.0, context.popFromStack(), 0);
            } catch (Exception ex) {
                return;
            }
            Assert.assertEquals(0.0, context.peekStack(), 0);
            Assert.assertEquals("Division by zero is not defined.", e.getMessage());
        }
    }
}
