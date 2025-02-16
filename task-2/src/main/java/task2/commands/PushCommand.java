package task2.commands;

import task2.Command;
import task2.Context;

public class PushCommand implements Command {

    @Override
    public void execute(Context context, String[] arguments) throws Exception {
        double number;
        try {
            number = context.getParameterValue(arguments[0]);
            context.pushOnStack(number);
        } catch (Exception e) {
            if (arguments[0].matches("[A-Za-zА-Яа-я]+[A-Za-z0-9А-Яа-я]*")) {
                throw new RuntimeException("Undefined parameter " + e.getMessage() + ".");
            }
            if (!arguments[0].matches("^[+-]?[0-9]+([,.][0-9]?)?$")) {
                throw new RuntimeException("It is possible to push only a number on the stack.");
            }
            context.pushOnStack(Double.parseDouble(arguments[0]));
        }
    }
}
