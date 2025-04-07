package task4;

import task4.factory.Factory;
import task4.factory.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory("test.txt");
        factory.start();
        SwingUtilities.invokeLater( () -> {
            MainWindow mainWindow = new MainWindow(factory);}
        );
    }
}