package task2.commands.binaryOps;

import task2.Context;

public class SumCommand extends BinaryOpCommand {
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        try {
            super.execute(context, arguments);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform summarization.");
        }
        context.pushOnStack(element1 + element2);
    }
}