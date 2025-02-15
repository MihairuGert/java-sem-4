package task2.commands.binaryOps;

import task2.Context;

public class SumCommand extends BinaryOpCommand {
    @Override
    public void execute(Context context, String[] arguments) {
        try {
            super.execute(context, arguments);
        } catch (Exception e) {
            System.out.println("Not enough elements to perform summarization.");
            return;
        }
        context.pushOnStack(element1 + element2);
    }
}