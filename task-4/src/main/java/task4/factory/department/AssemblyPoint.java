package task4.factory.department;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.threadpool.ThreadPool;

public class AssemblyPoint {
    private ThreadPool threadPool;
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    public AssemblyPoint(int workersNum, Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage) {
        threadPool = new ThreadPool(workersNum, 1000);
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        threadPool.addTask(new Worker(bodyStorage, engineStorage, accessoryStorage, carStorage));
    }
}
