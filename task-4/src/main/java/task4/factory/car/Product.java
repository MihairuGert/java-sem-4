package task4.factory.car;

import task4.utilities.Id;

public class Product {
    private Id id;

    public Product() {
        id = new Id(10);
    }

    public String getId() {
        return id.getId();
    }
}
