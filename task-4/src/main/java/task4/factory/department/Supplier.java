package task4.factory.department;

import task4.factory.car.details.Detail;

import static java.lang.Thread.sleep;

public class Supplier <T extends Detail> extends Department implements Runnable {
    private final Storage<T> storage;
    private final Creator<T> creator;

    public Supplier(Storage<T> storage, int speed, Class<T> clazz) {
        super(speed);
        this.storage = storage;
        creator = new Creator<>(clazz);
    }

    @Override
    public void run() {
        while (isWorking()) {
            supply();
            try {
                sleep(getSpeed());
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
