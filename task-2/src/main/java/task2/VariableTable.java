package task2;

import java.util.HashMap;

public class VariableTable {
    private final HashMap<String, Double> hashMap = new HashMap<>();
    public void put(String name, double value) {
        hashMap.put(name, value);
    }
    public double get(String name) throws Exception {
        if (!hashMap.containsKey(name)) {
            throw new Exception();
        }
        return hashMap.get(name);
    }
}
