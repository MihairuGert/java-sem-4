package task3.entity;

public class Entity {
    protected int x = 0;
    protected int y = 0;
    protected int xSize = 0;
    protected int ySize = 0;

    public int getxSize() {
        return xSize;
    }
    public int getySize() {
        return ySize;
    }
    public void setSize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }
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
