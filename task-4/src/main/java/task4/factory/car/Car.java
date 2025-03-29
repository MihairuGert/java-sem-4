package task4.factory.car;

import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;

public class Car extends Product {
    private Accessory accessory;
    private Body body;
    private Engine engine;

    public Car(Body body, Engine engine, Accessory accessory) {
        super();
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public String id() {
        return body.getId() + " " + engine.getId() + " " + accessory.getId() + " " + this.getId();
    }
}
