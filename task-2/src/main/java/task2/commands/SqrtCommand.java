package task2.commands;

import task2.Command;
import task2.Context;

public class SqrtCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        double element;
        try {
            element = context.popFromStack();
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform sqrt.");
        }
        if (element < 0) {
            context.pushOnStack(element);
            throw new RuntimeException("Square root of negative number is not defined.");
        }
        context.pushOnStack(Math.sqrt(element));
    }
}
