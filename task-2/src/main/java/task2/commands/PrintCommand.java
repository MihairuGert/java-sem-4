package task2.commands;

import task2.Command;
import task2.Context;

public class PrintCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) {
        double value = context.peekStack();
        if (value == Double.POSITIVE_INFINITY) {
            System.out.println("Stack is empty. Nothing to print.");
            return;
        }
        System.out.println(value);
    }
}
