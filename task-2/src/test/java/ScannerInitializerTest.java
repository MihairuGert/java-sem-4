import org.junit.Assert;
import org.junit.Test;
import task2.ScannerInitializer;

import java.util.Scanner;

public class ScannerInitializerTest {

    @Test
    public void chooseScannerTest() {
        ScannerInitializer scannerInitializer = new ScannerInitializer();
        String[] args = {"test.txt"};
        Scanner scanner;
        try {
            scanner = scannerInitializer.chooseScanner(args);
        } catch (Exception e) {
            return;
        }
        Assert.assertEquals("DEFINE a 4", scanner.next());
    }

    @Test
    public void chooseNotFoundScannerTest() {
        ScannerInitializer scannerInitializer = new ScannerInitializer();
        String[] args = {"test1.txt"};
        Scanner scanner;
        try {
            scanner = scannerInitializer.chooseScanner(args);
        } catch (Exception e) {
            Assert.assertEquals("File with this name does not exist.", e.getMessage());
        }
    }
}
