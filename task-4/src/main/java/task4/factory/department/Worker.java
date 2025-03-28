package task4.factory.department;

import task4.car.Car;
import task4.car.details.Accessory;
import task4.car.details.Body;
import task4.car.details.Engine;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class Worker extends Department implements Runnable{
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;
    // todo add other storages

    public Worker(Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, int speed) {
        super(speed);
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;

        cars = new LinkedList<>();
    }

    LinkedList<Car> cars;

    @Override
    public void run() {
        while (isWorking()) {
            try {
                Body body = bodyStorage.get();
                Engine engine = engineStorage.get();
                Accessory accessory = accessoryStorage.get();
                Car car = new Car(body, engine, accessory);
                cars.add(car);
                System.out.println("New car " + car.id());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
