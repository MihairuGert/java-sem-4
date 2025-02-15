package task2;
import java.util.Stack;

public class CalculatorStack {
    Stack<Double> stack;
    CalculatorStack() {
        stack = new Stack<Double>();
    }
    void push(double element) {
        stack.push(element);
    }
    double pop() {
        return stack.pop();
    }
}
