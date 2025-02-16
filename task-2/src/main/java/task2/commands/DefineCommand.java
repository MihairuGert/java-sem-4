package task2.commands;

import task2.Command;
import task2.Context;

public class DefineCommand implements Command {
    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        if (!arguments[0].matches("[A-Za-zА-Яа-я][A-Za-z0-9А-Яа-я]*")) {
            throw new RuntimeException("Parameter must not start with a number, or use any special characters.");
        }
        if (!arguments[1].matches("^[+-]?[0-9]+([,.][0-9]?)?$")) {
            throw new RuntimeException("Parameter must be initialised with a number.");
        }
        context.addNewParameter(arguments[0], Double.parseDouble(arguments[1]));
    }
}
