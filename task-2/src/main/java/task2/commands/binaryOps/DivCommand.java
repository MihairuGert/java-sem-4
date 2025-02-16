package task2.commands.binaryOps;

import task2.Context;

public class DivCommand extends BinaryOpCommand {
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        try {
            super.execute(context, arguments);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform division.");
        }
        if (element2 == 0) {
            context.pushOnStack(element2);
            context.pushOnStack(element1);
            throw new RuntimeException("Division by zero is not defined.");
        }
        context.pushOnStack(element1 / element2);
    }
}
