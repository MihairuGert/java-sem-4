package task3.weapon;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;
import task3.entity.Entity;

import java.awt.*;

public class Weapon {
    protected Entity closestEntity = null;
    protected double closestDistance = Double.MAX_VALUE;
    protected double range = Double.MAX_VALUE;
    protected int damage;

    private double getEuclideanMetric(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    private void calculateClosest(double alpha, Point point1, Point point2, Entity entity) {
        double dx = (point2.x - point1.x) * alpha;
        double dy = (point2.y - point1.y) * alpha;
        double distance = getEuclideanMetric(dx, dy);
        if (closestDistance > distance && distance <= range) {
            closestDistance = distance;
            closestEntity = entity;
        }
    }

    public void isHit(Point point1, Point point2, Entity entity) {
        SimpleMatrix A = new SimpleMatrix(2, 2, true,
                point2.x - point1.x, 0,
                point2.y - point1.y, -entity.getySize());
        SimpleMatrix b = new SimpleMatrix(2, 1, true,
                entity.getX() + entity.getxSize() - point1.x,
                entity.getY() - point1.y);
        solveAndCheck(A, b, point1, point2, entity);

        A = new SimpleMatrix(2, 2, true,
                point2.y - point1.y, 0,
                point2.x - point1.x, -entity.getxSize());
        b = new SimpleMatrix(2, 1, true,
                entity.getY() + entity.getySize() - point1.y,
                entity.getX() - point1.x);
        solveAndCheck(A, b, point1, point2, entity);

        A = new SimpleMatrix(2, 2, true,
                point2.x - point1.x, 0,
                point2.y - point1.y, -entity.getySize());
        b = new SimpleMatrix(2, 1, true,
                entity.getX() - point1.x,
                entity.getY() - point1.y);
        solveAndCheck(A, b, point1, point2, entity);

        A = new SimpleMatrix(2, 2, true,
                point2.y - point1.y, 0,
                point2.x - point1.x, -entity.getxSize());
        b = new SimpleMatrix(2, 1, true,
                entity.getY() - point1.y,
                entity.getX() - point1.x);
        solveAndCheck(A, b, point1, point2, entity);
    }

    private void solveAndCheck(SimpleMatrix A, SimpleMatrix b, Point point1, Point point2, Entity entity) {
        try {
            SimpleMatrix x = A.solve(b);
            double alpha = x.get(0);
            double beta = x.get(1);
            if (alpha >= 0 && alpha <= 1 && beta >= 0 && beta <= 1) {
                calculateClosest(alpha, point1, point2, entity);
            }
        } catch (SingularMatrixException ignored) {
        }
    }

    public Entity whoWasKilled() {
        Entity entity = closestEntity;
        closestEntity = null;
        closestDistance = Double.MAX_VALUE;
        return entity;
    }
}