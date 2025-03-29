package task4.factory.department;

import task4.factory.car.Car;
import task4.threadpool.ThreadPool;

public class Controller extends Department implements Runnable{
    private final Storage<Car> carStorage;
    private final Workers workers;

    public Controller(Storage<Car> carStorage, Workers workers) {
        super(1);
        this.carStorage = carStorage;
        this.workers = workers;
    }

    @Override
    public void run() {
        while (!isWorking()) {
            try {
                carStorage.wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            if (carStorage.getProdNum() < 1) {
                workers.assembleCar();
            }
        }
    }
}
