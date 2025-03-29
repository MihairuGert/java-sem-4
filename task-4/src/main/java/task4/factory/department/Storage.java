package task4.factory.department;

import task4.factory.car.Product;

import java.util.LinkedList;
import java.util.Queue;

public class Storage <T extends Product> {
    private final Queue<T> details;
    private final int capacity;

    public Storage(int capacity) {
        details = new LinkedList<>();
        if (capacity == -1) {
            this.capacity = 10;
            return;
        }
        this.capacity = capacity;
    }

    public synchronized int getProdNum() {
        return details.size();
    }

    public synchronized void add(T product) throws Exception {
        while (details.size() > capacity) {
            wait();
        }
        //System.out.println("Product with id = " + detail.getId() + " was added.");
        details.add(product);
        notifyAll();
    }

    public synchronized T get() throws Exception {
        while (details.isEmpty()) {
            wait();
        }
        T detail = details.remove();
        //System.out.println("Product with id = " + detail.getId() + " was taken.");
        notifyAll();
        return detail;
    }
}
