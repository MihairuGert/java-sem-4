import org.junit.Assert;
import org.junit.Test;
import task1.Reader;

import java.io.*;

public class ReaderTest {

    @Test
    public void getInputStringTest() {
        String input = "1234\r\n";
        String inputExpected = "1234";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        InputStream originalInputStream = System.in;
        System.setIn(byteArrayInputStream);

        Reader reader = new Reader();
        Assert.assertEquals(inputExpected, reader.getInputString());

        System.setIn(originalInputStream);
    }
}
