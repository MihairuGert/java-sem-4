package task3.server.commands.player;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;
import task3.entity.Entity;

import java.awt.*;

public class Fire {
    public static boolean isHit(Point point1, Point point2, Entity entity) {
        SimpleMatrix A = new SimpleMatrix(2,2,true,
                entity.getxSize(), point1.x - point2.x, 0, point1.y - point2.y);
        SimpleMatrix b = new SimpleMatrix(2,1,true,
                point1.x - entity.getX(), point1.y - entity.getY() - entity.getySize());
        SimpleMatrix x;
        try {
            x = A.solve(b);
        } catch ( SingularMatrixException e ) {
            return false;
        }
        if (x.get(0) <= 1.0 && x.get(0) >= 0 && x.get(1) <= 1 && x.get(1) >= 0) {
            return true;
        }

        A = new SimpleMatrix(2,2,true,
                0, point1.x - point2.x, -entity.getySize(), point1.y - point2.y);
        try {
            x = A.solve(b);
        } catch ( SingularMatrixException e ) {
            return false;
        }
        if (x.get(0) <= 1.0 && x.get(0) >= 0 && x.get(1) <= 1 && x.get(1) >= 0) {
            return true;
        }

        A = new SimpleMatrix(2,2,true,
                -entity.getxSize(), point1.x - point2.x, 0, point1.y - point2.y);
        b = new SimpleMatrix(2,1,true,
                point1.x - entity.getX() - entity.getxSize(), point1.y - entity.getY());
        try {
            x = A.solve(b);
        } catch ( SingularMatrixException e ) {
            return false;
        }
        if (x.get(0) <= 1.0 && x.get(0) >= 0 && x.get(1) <= 1 && x.get(1) >= 0) {
            return true;
        }

        A = new SimpleMatrix(2,2,true,
                0, point1.x - point2.x, entity.getySize(), point1.y - point2.y);
        try {
            x = A.solve(b);
        } catch ( SingularMatrixException e ) {
            return false;
        }
        if (x.get(0) <= 1.0 && x.get(0) >= 0 && x.get(1) <= 1 && x.get(1) >= 0) {
            return true;
        }

        return false;
    }
}
