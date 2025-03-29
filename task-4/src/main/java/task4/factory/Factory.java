package task4.factory;

import task4.factory.car.Car;
import task4.factory.car.Product;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.factory.department.Storage;
import task4.factory.department.Supplier;
import task4.factory.department.Workers;
import task4.utilities.Config;

public class Factory {
    private Config config;

    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Engine> engineStorage;

    private Storage<Car> carStorage;

    private Supplier<Body> bodySupplier;
    // todo thread pool
    private Supplier<Accessory> accessorySupplier;
    private Supplier<Engine> engineSupplier;

    private void initStorages() {
        bodyStorage = new Storage<>(Integer.parseInt(config.getFieldValue("BodyStorageCapacity")));
        accessoryStorage = new Storage<>(Integer.parseInt(config.getFieldValue("AccessoryStorageCapacity")));
        engineStorage = new Storage<>(Integer.parseInt(config.getFieldValue("EngineStorageCapacity")));
        carStorage = new Storage<>(Integer.parseInt(config.getFieldValue("CarStorageCapacity")));
    }

    private void initSuppliers() {
        bodySupplier = new Supplier<>(bodyStorage, Integer.parseInt(config.getFieldValue("BodySupplierSpeed")), Body.class);
        // todo thread pool
        accessorySupplier = new Supplier<>(accessoryStorage, Integer.parseInt(config.getFieldValue("AccessorySupplierSpeed")), Accessory.class);
        engineSupplier = new Supplier<>(engineStorage, Integer.parseInt(config.getFieldValue("EngineSupplierSpeed")), Engine.class);
    }

    public Factory(String path) {
        config = new Config(path);
        initStorages();
        initSuppliers();
        //config.DBGMSG();
    }

    public void start() {
        new Thread(bodySupplier).start();
        new Thread(accessorySupplier).start();
        new Thread(engineSupplier).start();

        Workers workers = new Workers(10, 1000, bodyStorage, engineStorage, accessoryStorage, carStorage);

        for (int i = 0; i < 100; i++) {
            workers.assembleCar();
        }

        while (true) {
            try {
                System.out.println(carStorage.get().id());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
