package task4.factory.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task4.factory.car.Car;

import static java.lang.Thread.sleep;

public class Dealer extends Department implements Runnable {
    private final Storage<Car> carStorage;
    private static volatile Logger logger = LoggerFactory.getLogger(Dealer.class);
    private int number;

    public Dealer(Storage<Car> carStorage, int speed, int number) {
        super(speed);
        this.carStorage = carStorage;
        this.number = number;
    }

    @Override
    public void run() {
        while (isWorking()) {
            try {
                Car car = carStorage.get();
                logger.info("Dealer {}:Auto {}(Body: {}, Motor: {}, Accessory: {})", number, car.getId(), car.bodyId(), car.engineId(), car.accessoryId());
                sleep(getSpeed());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
