import org.junit.Assert;
import org.junit.Test;
import task2.Context;
import task2.commands.SqrtCommand;

public class SqrtCommandTest {

    @Test
    public void ZeroDivTest() {
        Context context = new Context();
        SqrtCommand sqrtCommand = new SqrtCommand();
        context.pushOnStack(-1);
        try {
            sqrtCommand.execute(context, new String[] {});
        } catch (Exception e) {
            Assert.assertEquals("Square root of negative number is not defined.", e.getMessage());
        }
    }
}
