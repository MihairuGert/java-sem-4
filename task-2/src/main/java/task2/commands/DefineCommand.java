package task2.commands;

import task2.Command;
import task2.Context;

public class DefineCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) {
        if (!arguments[0].matches("[A-Za-zА-Яа-я][A-Za-z0-9А-Яа-я]*")) {
            System.out.println("Parameter must not start with a number, or use any special characters.");
            return;
        }
        if (!arguments[1].matches("^[+-]?[0-9]+([,.][0-9]?)?$")) {
            System.out.println("Parameter must be initialised with a number.");
            return;
        }
        context.addNewParameter(arguments[0], Double.parseDouble(arguments[1]));
    }
}
