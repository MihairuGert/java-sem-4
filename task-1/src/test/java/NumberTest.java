import org.junit.Assert;
import org.junit.Test;
import task1.Number;

import java.util.HashMap;

public class NumberTest {

    @Test
    public void numberConstructorTest() {
        String symPool = "0123456789";
        int lettersNum = 4;
        Number number = new Number(symPool, lettersNum);
        Assert.assertTrue(number.getNumberString().matches("[0-9]{4}"));
        HashMap<Character, Integer> lettersCount = new HashMap<>();
        for (int i = 0; i < symPool.length(); i++) {
            lettersCount.put(symPool.charAt(i), 0);
        }
        for (int i = 0; i < number.getNumberString().length(); i++) {
            int num = lettersCount.get(number.getNumberString().charAt(i));
            lettersCount.put(number.getNumberString().charAt(i), num + 1);
        }
        lettersCount.forEach((key, value) -> Assert.assertTrue(value <= 1));
    }
}
