package task4.factory.department;

import task4.car.details.Detail;

import java.util.LinkedList;
import java.util.Queue;

public class Storage <T extends Detail> {
    private final Queue<T> details;
    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        details = new LinkedList<>();
    }

    public synchronized void add(T detail) throws Exception {
        while (details.size() > capacity) {
            wait();
        }
        System.out.println("Detail with id = " + detail.getId() + " was added.");
        details.add(detail);
        notify();
    }

    public synchronized T get() throws Exception {
        while (details.isEmpty()) {
            wait();
        }
        T detail = details.remove();
        System.out.println("Detail with id = " + detail.getId() + " was taken.");
        notify();
        return detail;
    }
}
