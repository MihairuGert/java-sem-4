package task4.car;

import task4.car.details.Accessory;
import task4.car.details.Body;
import task4.car.details.Engine;

import java.util.LinkedList;

public class Car {
    private LinkedList<Accessory> accessories;
    private Body body;
    private Engine engine;

    public Car(Body body, Engine engine) {
        accessories = new LinkedList<>();
        this.body = body;
        this.engine = engine;
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }
}
