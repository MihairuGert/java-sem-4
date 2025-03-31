package task4.factory.department;

import task4.factory.car.Car;

public class Controller extends Department implements StorageObserver{
    private final AssemblyPoint assemblyPoint;

    public Controller(AssemblyPoint assemblyPoint, boolean isWorking) {
        super(1, isWorking);
        this.assemblyPoint = assemblyPoint;
    }

    @Override
    public void productTaken() {
        assemblyPoint.assembleCar();
    }
}
