package task4.utilities;

public class Id {
    public String getId() {
        return id;
    }

    private final String id;

    public Id (int len) {
        id = generateId(len);
    }

    private String generateId(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        String letterPool = "0123456789abcdef";
        for (int i = 0; i < len; i++) {
            stringBuilder.append(letterPool.charAt((int)(Math.random() * 1000) % letterPool.length()));
        }
        return stringBuilder.toString();
    }
}
