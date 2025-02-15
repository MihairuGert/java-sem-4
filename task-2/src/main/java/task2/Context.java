package task2;

public class Context {
    private final CalculatorStack calculatorStack = new CalculatorStack();
    private final VariableTable variableTable = new VariableTable();

    public void pushOnStack(double value) {
        calculatorStack.push(value);
    }
    public double popFromStack() throws Exception {
        return calculatorStack.pop();
    }

    public void addNewParameter(String name, double value) {
        variableTable.put(name, value);
    }
    public double getParameterValue(String name) throws Exception {
        return variableTable.get(name);
    }

}
