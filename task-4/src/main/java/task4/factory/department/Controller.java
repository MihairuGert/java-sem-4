package task4.factory.department;

import task4.factory.car.Car;

public class Controller extends Department implements Runnable{
    private final Storage<Car> carStorage;
    private final AssemblyPoint assemblyPoint;

    public Controller(Storage<Car> carStorage, AssemblyPoint assemblyPoint) {
        super(1);
        this.carStorage = carStorage;
        this.assemblyPoint = assemblyPoint;
    }

    @Override
    public void run() {
        while (isWorking()) {
            synchronized (carStorage) {
                try {
                    carStorage.wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                if (carStorage.getProdNum() < 1) {
                    assemblyPoint.assembleCar();
                }
            }
        }
    }
}
