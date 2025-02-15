package task2;
import java.util.Stack;

public class CalculatorStack {
    private final Stack<Double> stack = new Stack<>();
    public void push(double element) {
        stack.push(element);
    }
    public double pop() throws Exception{
        if (stack.empty()) {
            throw new Exception();
        }
        return stack.pop();
    }
}
