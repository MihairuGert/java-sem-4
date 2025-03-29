package task4;

import task4.factory.Factory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory("test.txt");
        factory.start();
    }
}