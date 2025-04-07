package task4.factory.department;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;

public class Worker implements Runnable {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public Worker(Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage) {
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void run() {
        try {
            Body body = bodyStorage.get();
            Engine engine = engineStorage.get();
            Accessory accessory = accessoryStorage.get();
            Car car = new Car(body, engine, accessory);
            carStorage.add(car);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
