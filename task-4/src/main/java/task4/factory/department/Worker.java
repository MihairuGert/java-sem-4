package task4.factory.department;

import task4.car.details.Body;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class Worker extends Department implements Runnable{
    Storage<Body> bodyStorage;
    // todo add other storages

    public Worker(Storage<Body> bodyStorage, int speed) {
        super(speed);
        this.bodyStorage = bodyStorage;
    }

    @Override
    public void run() {
        LinkedList<Body> bodies = new LinkedList<>();
        int ticks = 0;
        while (isWorking()) {
            try {
                if (ticks % 3 == 0) {
                    bodies.forEach((Body body) -> {
                        System.out.println("Now I have: " + body.getId());
                    });
                }
                Body body = bodyStorage.get();
                bodies.add(body);
                sleep(getSpeed());
                ticks++;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
