package task4.factory.department;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.threadpool.ThreadPool;

public class Worker implements Runnable {
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

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
