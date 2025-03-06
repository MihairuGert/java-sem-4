package task3.weapon;

import task3.entity.Entity;

import java.awt.*;
import java.io.Serializable;

public class Weapon implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Entity closestEntity = null;
    protected double closestDistance = Double.MAX_VALUE;
    protected double range = Double.MAX_VALUE;
    protected int damage;

    private void calculateClosest(double projectileLength, Entity entity) {
        if (closestDistance > projectileLength && projectileLength <= range) {
            closestDistance = projectileLength;
            closestEntity = entity;
        }
    }

    private void isHitVertical(Point point1, Point point2, Entity entity, int xSize, double projectileLength) {
        double alpha = ((double) entity.getX() + xSize - point1.x) / (point2.x - point1.x);
        if (alpha < 0 || alpha > 1) {
            return;
        }
        double y = point1.y + alpha * (point2.y - point1.y);
        if (y <= entity.getY() + entity.getySize() && y >= entity.getY()) {
            calculateClosest(alpha * projectileLength, entity);
        }
    }

    private void isHitHorizontal(Point point1, Point point2, Entity entity, int ySize, double projectileLength) {
        double alpha = ((double) entity.getY() + ySize - point1.y) / (point2.y - point1.y);
        if (alpha < 0 || alpha > 1) {
            return;
        }
        double x = point1.x + alpha * (point2.x - point1.x);
        if (x <= entity.getX() + entity.getxSize() && x >= entity.getX()) {
            calculateClosest(alpha * projectileLength, entity);
        }
    }

    public void isHit(Point point1, Point point2, Entity entity) {
        double projectileLength = Math.hypot(point2.x - point1.x, point2.y - point1.y);
        isHitVertical(point1, point2, entity, 0, projectileLength);
        isHitVertical(point1, point2, entity, entity.getxSize(), projectileLength);
        isHitHorizontal(point1, point2, entity, 0, projectileLength);
        isHitHorizontal(point1, point2, entity, entity.getySize(), projectileLength);
    }

    public Entity whoWasKilled() {
        Entity entity = closestEntity;
        closestEntity = null;
        closestDistance = Double.MAX_VALUE;
        return entity;
    }
}
