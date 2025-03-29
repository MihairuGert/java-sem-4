package task4.factory.car;

import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;

public class Car extends Product {
    private final Accessory accessory;
    private final Body body;
    private final Engine engine;

    public Car(Body body, Engine engine, Accessory accessory) {
        super();
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public String bodyId() {
        return body.getId();
    }

    public String engineId() {
        return engine.getId();
    }

    public String accessoryId() {
        return accessory.getId();
    }
}
