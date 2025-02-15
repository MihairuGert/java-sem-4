package task2.commands;

import task2.Command;
import task2.Context;

public class SqrtCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) {
        double element;
        try {
            element = context.popFromStack();
        } catch (Exception e) {
            System.out.println("Not enough elements to perform sqrt.");
            return;
        }
        if (element < 0) {
            System.out.println("Sqrt of negative number is not defined.");
            return;
        }
        context.pushOnStack(Math.sqrt(element));
    }
}
