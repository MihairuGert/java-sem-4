package task4.factory.department;

import task4.factory.car.Car;

import static java.lang.Thread.sleep;

public class Dealer extends Department implements Runnable {
    private final Storage<Car> carStorage;

    public Dealer(Storage<Car> carStorage, int speed) {
        super(speed);
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        while (isWorking()) {
            try {
                carStorage.get();
                sleep(getSpeed());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
