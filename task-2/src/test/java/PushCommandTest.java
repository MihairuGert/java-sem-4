import org.junit.Assert;
import org.junit.Test;
import task2.Context;
import task2.commands.PushCommand;

public class PushCommandTest {
    @Test
    public void PushUndefinedParamTest() {
        Context context = new Context();
        PushCommand pushCommand = new PushCommand();
        try {
            pushCommand.execute(context, new String[] {"a"});
        } catch (Exception e) {
            Assert.assertEquals("Undefined parameter a.", e.getMessage());
        }
        try {
            pushCommand.execute(context, new String[] {"@#%$@#$"});
        } catch (Exception e) {
            Assert.assertEquals("It is possible to push only a number on the stack, not @#%$@#$.", e.getMessage());
        }
    }
}
