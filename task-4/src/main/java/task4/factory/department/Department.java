package task4.factory.department;

public class Department {
    public int getSpeed() {
        return speed;
    }

    public boolean isWorking() {
        return isWorking;
    }

    private int speed;
    private boolean isWorking = true;

    Department(int speed) {
        this.speed = speed;
    }
}
