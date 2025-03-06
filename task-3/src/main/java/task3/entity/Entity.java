package task3.entity;

import java.awt.*;
import java.io.Serializable;

public class Entity implements Serializable {
    protected int x = 0;
    protected int y = 0;
    protected int xSize = 0;
    protected int ySize = 0;
    protected boolean isDead = false;
    transient protected Image texture = null;

    protected double rotationAngle = 0;

    public double getRotationAngle() {
        return rotationAngle;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture() {}

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

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

    public void kill() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
