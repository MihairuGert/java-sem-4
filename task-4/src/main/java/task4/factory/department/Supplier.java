package task4.factory.department;

import task4.car.details.Detail;

import static java.lang.Thread.sleep;

public class Supplier <T extends Detail> implements Runnable {
    private final Storage<T> storage;
    private final Creator<T> creator;

    public Supplier(Storage<T> storage, int speed, Class<T> clazz) {
        this.storage = storage;
        this.speed = speed;
        creator = new Creator<>(clazz);
    }

    private final int speed;
    private boolean isWorking = true;

    @Override
    public void run() {
        while (isWorking) {
            supply();
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    private void supply() {
        T detail = creator.createInstance();
        try {
            storage.add(detail);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private record Creator<T>(Class<T> clazz) {

        public T createInstance() {
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Error while creating an instance.");
                }
            }

        }
}
