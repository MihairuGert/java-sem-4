package task4.factory.department;

public class Department {
    public int getSpeed() {
        return speed;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking() {
        isWorking = false;
    }

    private int speed;
    private volatile boolean isWorking;

    Department(int speed, boolean isWorking) {
        if (speed == -1) {
            this.speed = 10;
            return;
        }
        this.isWorking = isWorking;
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            return;
        }
        this.speed = speed;
    }
}
