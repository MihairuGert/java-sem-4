package task2.commands;

import task2.Command;
import task2.Context;

public class PopCommand implements Command {

    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        double element;
        try {
            element = context.popFromStack();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (arguments.length == 0) {
            return;
        }
        if (arguments[0].isEmpty()) {
            return;
        }
        if (!arguments[0].matches("[A-Za-zА-Яа-я][A-Za-z0-9А-Яа-я]*")) {
            throw new RuntimeException("Parameter must not start with a number, or use any special characters.");
        }
        context.addNewParameter(arguments[0], element);
    }
}
