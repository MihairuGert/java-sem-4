package task4.factory;

import task4.car.details.Body;
import task4.factory.department.Storage;
import task4.factory.department.Supplier;
import task4.factory.department.Worker;
import task4.utilities.Config;

public class Factory {
    private Config config;

    public Factory(String path) {
        config = new Config(path);
        Storage<Body> bodyStorage = new Storage<>(Integer.parseInt(config.getFieldValue("BodyStorageCapacity")));
        Supplier<Body> bodySupplier = new Supplier<>(bodyStorage, Integer.parseInt(config.getFieldValue("BodySupplierSpeed")), Body.class);
        Worker worker = new Worker(bodyStorage, 2000);
        new Thread(bodySupplier).start();
        new Thread(worker).start();
        config.DBGMSG();
    }
}
