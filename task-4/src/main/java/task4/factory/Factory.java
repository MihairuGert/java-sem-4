package task4.factory;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.factory.department.Storage;
import task4.factory.department.Suppliers;
import task4.factory.department.Workers;
import task4.factory.ui.MainWindow;
import task4.factory.ui.MainWindowListener;
import task4.utilities.Config;

import javax.swing.*;

public class Factory implements MainWindowListener {
    private Config config;

    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Engine> engineStorage;

    private Storage<Car> carStorage;

    private Suppliers<Body> bodySuppliers;
    // todo thread pool
    private Suppliers<Accessory> accessorySuppliers;
    private Suppliers<Engine> engineSuppliers;

    private void initStorages() {
        bodyStorage = new Storage<>(Integer.parseInt(config.getFieldValue("BodyStorageCapacity")));
        accessoryStorage = new Storage<>(Integer.parseInt(config.getFieldValue("AccessoryStorageCapacity")));
        engineStorage = new Storage<>(Integer.parseInt(config.getFieldValue("EngineStorageCapacity")));
        carStorage = new Storage<>(Integer.parseInt(config.getFieldValue("CarStorageCapacity")));
    }

    private void initSuppliers() {
        bodySuppliers = new Suppliers<>(bodyStorage, Integer.parseInt(config.getFieldValue("BodySupplierSpeed")), Body.class);
        // todo thread pool
        accessorySuppliers = new Suppliers<>(accessoryStorage, Integer.parseInt(config.getFieldValue("AccessorySupplierSpeed")), Accessory.class);
        engineSuppliers = new Suppliers<>(engineStorage, Integer.parseInt(config.getFieldValue("EngineSupplierSpeed")), Engine.class);
    }

    public Factory(String path) {
        config = new Config(path);
        initStorages();
        initSuppliers();
        SwingUtilities.invokeLater( () -> {
            MainWindow mainWindow = new MainWindow(this);}
        );
        //config.DBGMSG();
    }

    public void start() {
        new Thread(bodySuppliers).start();
        new Thread(accessorySuppliers).start();
        new Thread(engineSuppliers).start();

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
