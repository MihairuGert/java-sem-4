package task3.entity;

public class Entity {
    protected int x = 0;
    protected int y = 0;
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
