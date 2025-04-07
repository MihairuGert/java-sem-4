import org.junit.Assert;
import org.junit.Test;
import task2.Context;

public class ContextTest {

    @Test
    public void pushOnStackTest() {
        Context context = new Context();
        double testValue1 = 1.0;
        double testValue2 = 2.0;
        context.pushOnStack(testValue1);
        context.pushOnStack(testValue2);
        try {
            double value = context.popFromStack();
            Assert.assertEquals(testValue2, value, 0);
            Assert.assertEquals(testValue1, context.peekStack(), 0);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void pushOnStackLargeTest() {
        Context context = new Context();

        double startValue = 1.5;
        double step = 1;
        double numberOfSteps = 1000;
        double limit = numberOfSteps * step + startValue;

        double testValue;
        for (testValue = startValue; testValue <= limit; testValue += step) {
            context.pushOnStack(testValue);
        }
        int i = 0;
        for (; testValue > startValue; testValue -= step) {
            try {
                double value = context.popFromStack();
                Assert.assertEquals(limit - step * i, value, 0);
            } catch (Exception e) {
                return;
            }
            i++;
        }
    }

    @Test
    public void popFromStackTest() {
        Context context = new Context();
        double testValue = 1.0;
        context.pushOnStack(testValue);
        try {
            double value = context.popFromStack();
            Assert.assertEquals(testValue, value, 0);
            value = context.peekStack();
            Assert.assertEquals(Double.POSITIVE_INFINITY, value, 0);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void popFromEmptyStackTest() {
        Context context = new Context();
        try {
            double value = context.popFromStack();
        } catch (Exception e) {
            Assert.assertEquals("Stack is empty. Nothing to pop.", e.getMessage());
        }
    }

    @Test
    public void peekStackTest() {
        Context context = new Context();
        double testValue = 1.0;
        context.pushOnStack(testValue);
        try {
            double value = context.peekStack();
            Assert.assertEquals(testValue, value, 0);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void addNewParameterTest() {
        Context context = new Context();
        String testParameter = "test";
        double testValue = 1.0;
        context.addNewParameter(testParameter, testValue);
        try {
            double value = context.getParameterValue(testParameter);
            Assert.assertEquals(testValue, value, 0);
        } catch (Exception e) {
            return;
        }
        testValue = 2.0;
        context.addNewParameter(testParameter, testValue);
        try {
            double value = context.getParameterValue(testParameter);
            Assert.assertEquals(testValue, value, 0);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void getParameterValueTest() {
        Context context = new Context();
        String testParameter = "test";
        double testValue = 1.0;
        context.addNewParameter(testParameter, testValue);
        try {
            double value = context.getParameterValue(testParameter + "1");
        } catch (Exception e) {
            Assert.assertEquals(testParameter + "1", e.getMessage());
        }
    }
}
