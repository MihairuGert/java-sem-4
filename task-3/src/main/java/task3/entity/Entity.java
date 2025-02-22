package task3.entity;

public class Entity {
    protected int x;
    protected int y;
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
