package task4.factory;

import task4.factory.car.Car;
import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.factory.department.Storage;
import task4.factory.department.Supplier;
import task4.factory.department.Workers;
import task4.factory.ui.MainWindow;
import task4.factory.ui.MainWindowListener;
import task4.utilities.Config;

import javax.swing.*;
import java.util.ArrayList;

public class Factory implements MainWindowListener {
    private Config config;

    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Engine> engineStorage;

    private Storage<Car> carStorage;

    private Supplier<Body> bodySupplier;
    private ArrayList<Supplier<Accessory>> accessorySuppliers;
    private Supplier<Engine> engineSupplier;

    private void initStorages() {
        bodyStorage = new Storage<>(Integer.parseInt(config.getFieldValue("BodyStorageCapacity")));
        accessoryStorage = new Storage<>(Integer.parseInt(config.getFieldValue("AccessoryStorageCapacity")));
        engineStorage = new Storage<>(Integer.parseInt(config.getFieldValue("EngineStorageCapacity")));
        carStorage = new Storage<>(Integer.parseInt(config.getFieldValue("CarStorageCapacity")));
    }

    private void initSuppliers() {
        bodySupplier = new Supplier<>(bodyStorage, Integer.parseInt(config.getFieldValue("BodySupplierSpeed")), Body.class);
        accessorySuppliers = new ArrayList<>();
        int accessorySuppliersSize = Integer.parseInt(config.getFieldValue("AccessorySuppliers"));
        if (accessorySuppliersSize == -1) {
            accessorySuppliersSize = 10;
        }
        for (int i = 0; i < accessorySuppliersSize; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Integer.parseInt(config.getFieldValue("AccessorySupplierSpeed")), Accessory.class));
        }
        engineSupplier = new Supplier<>(engineStorage, Integer.parseInt(config.getFieldValue("EngineSupplierSpeed")), Engine.class);
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
        new Thread(bodySupplier).start();
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) {
            new Thread(accessorySupplier).start();
        }
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

    @Override
    public FactoryStat getStat() {
        return new FactoryStat(engineStorage.getProdNum(), engineStorage.getEverCreated(),
                bodyStorage.getProdNum(), bodyStorage.getEverCreated(),
                accessoryStorage.getProdNum(), accessoryStorage.getEverCreated(),
                carStorage.getProdNum(), carStorage.getEverCreated(), 10);
    }

}
