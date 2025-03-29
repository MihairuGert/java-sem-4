package task4.factory.department;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.threadpool.ThreadPool;

public class Workers extends Department {
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    private ThreadPool workers;

    public Workers(int workersNum, int speed, Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage) {
        super(speed);
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;

        workers = new ThreadPool(workersNum, 100);
    }

    private class CarAssembly implements Runnable {
        @Override
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

    public void assembleCar() {
        workers.addTask(new CarAssembly());
    }
}
