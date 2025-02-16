package task2.commands.binaryOps;

import task2.Command;
import task2.Context;

public class BinaryOpCommand implements Command {
    double element1;
    double element2;
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        try {
            element1 = context.popFromStack();
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform operation.");
        }
        try {
            element2 = context.popFromStack();
        } catch (Exception e) {
            context.pushOnStack(element1);
            throw new RuntimeException("Not enough elements to perform operation.");
        }
    }
}
