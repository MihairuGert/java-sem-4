package task4.factory;

import task4.car.details.Accessory;
import task4.car.details.Body;
import task4.car.details.Engine;
import task4.factory.department.Storage;
import task4.factory.department.Supplier;
import task4.factory.department.Worker;
import task4.utilities.Config;

public class Factory {
    private Config config;

    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Engine> engineStorage;

    private Supplier<Body> bodySupplier;
    // todo thread pool
    private Supplier<Accessory> accessorySupplier;
    private Supplier<Engine> engineSupplier;

    private void initStorages() {
        bodyStorage = new Storage<>(Integer.parseInt(config.getFieldValue("BodyStorageCapacity")));
        accessoryStorage = new Storage<>(Integer.parseInt(config.getFieldValue("AccessoryStorageCapacity")));
        engineStorage = new Storage<>(Integer.parseInt(config.getFieldValue("EngineStorageCapacity")));
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

        Worker worker = new Worker(bodyStorage, engineStorage, accessoryStorage, 2000);

        new Thread(bodySupplier).start();
        new Thread(accessorySupplier).start();
        new Thread(engineSupplier).start();

        new Thread(worker).start();
        config.DBGMSG();
    }
}
