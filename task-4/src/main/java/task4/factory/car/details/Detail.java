package task4.factory.car.details;

import task4.utilities.Id;

public class Detail {
    private Id id;

    public Detail() {
        id = new Id(10);
    }

    public String getId() {
        return id.getId();
    }
}
