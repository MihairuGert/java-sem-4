package task2.commands;

import task2.Command;
import task2.Context;

public class PrintCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        double value = context.peekStack();
        if (value == Double.POSITIVE_INFINITY) {
            throw new RuntimeException("Stack is empty. Nothing to print.");
        }
        System.out.println(value);
    }
}
