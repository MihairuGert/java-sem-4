package task2.commands;

import task2.Command;
import task2.Context;

public class PopCommand implements Command {

    @Override
    public void execute(Context context, String[] arguments) {
        double element;
        try {
            element = context.popFromStack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (arguments.length == 0) {
            return;
        }
        if (arguments[0].isEmpty()) {
            return;
        }
        if (!arguments[0].matches("[A-Za-zА-Яа-я][A-Za-z0-9А-Яа-я]*")) {
            System.out.println("Parameter must not start with a number, or use any special characters.");
            return;
        }
        context.addNewParameter(arguments[0], element);
    }
}
