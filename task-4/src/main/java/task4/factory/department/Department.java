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
    private boolean isWorking = true;

    Department(int speed) {
        if (speed == -1) {
            this.speed = 10;
            return;
        }
        this.speed = speed;
    }
}
