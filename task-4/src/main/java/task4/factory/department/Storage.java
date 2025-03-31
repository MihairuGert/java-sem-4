package task4.factory.department;

import task4.factory.car.Product;

import java.util.LinkedList;
import java.util.Queue;

public class Storage <T extends Product> {
    private final Queue<T> details;
    private final int capacity;

    public long getEverCreated() {
        return everCreated;
    }

    private long everCreated = 0;

    public Storage(int capacity) {
        details = new LinkedList<>();
        if (capacity == -1) {
            this.capacity = 10;
            return;
        }
        this.capacity = capacity;
    }

    public void setStorageObserver(StorageObserver storageObserver) {
        this.storageObserver = storageObserver;
    }

    private StorageObserver storageObserver = null;

    public synchronized int getProdNum() {
        return details.size();
    }

    public synchronized void add(T product) throws Exception {
        while (details.size() >= capacity) {
            wait();
        }
        everCreated++;
        details.add(product);
        notifyAll();
    }

    public synchronized T get() throws Exception {
        if (storageObserver != null)
            storageObserver.productTaken();
        while (details.isEmpty()) {
            wait();
        }
        T detail = details.remove();
        notifyAll();
        return detail;
    }
}
