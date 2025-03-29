package task4.factory.department;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.threadpool.ThreadPool;

public class AssemblyPoint {
    private final ThreadPool threadPool;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

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

    public int getQueueSize() {
        return threadPool.getTaskQueueSize();
    }
}
