package task2.commands;

import task2.Command;
import task2.Context;

public class PushCommand implements Command {

    @Override
    public void execute(Context context, String[] arguments) {
        double number;
        try {
            number = context.getParameterValue(arguments[0]);
            context.pushOnStack(number);
        } catch (Exception e) {
            if (arguments[0].matches("[A-Za-zА-Яа-я]+[A-Za-z0-9А-Яа-я]*")) {
                System.out.println("Undefined parameter " + e.getMessage() + ".");
                return;
            }
            if (!arguments[0].matches("^[+-]?[0-9]+([,.][0-9]?)?$")) {
                System.out.println("It is possible to push only a number on the stack.");
                return;
            }
            context.pushOnStack(Double.parseDouble(arguments[0]));
        }
    }
}
