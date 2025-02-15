package task2.commands.binaryOps;

import task2.Context;

public class DivCommand extends BinaryOpCommand {
    @Override
    public void execute(Context context, String[] arguments) {
        try {
            super.execute(context, arguments);
        } catch (Exception e) {
            System.out.println("Not enough elements to perform division.");
            return;
        }
        if (element2 == 0) {
            System.out.println("Division by zero is not defined.");
            return;
        }
        context.pushOnStack(element1 / element2);
    }
}
