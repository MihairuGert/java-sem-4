package task4.factory.car;

import task4.factory.car.details.Accessory;
import task4.factory.car.details.Body;
import task4.factory.car.details.Engine;
import task4.utilities.Id;

public class Car {
    private Id id;

    private Accessory accessory;
    private Body body;
    private Engine engine;

    public Car(Body body, Engine engine, Accessory accessory) {
        id = new Id(10);
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public String id() {
        return body.getId() + " " + engine.getId() + " " + accessory.getId() + " " + id.getId();
    }
}
