package task3.server;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;
import task3.entity.Entity;

import java.awt.*;

public class Bullet {
    public static boolean isHit(Point point1, Point point2, Entity entity) {
        System.out.println(point1);
        System.out.println(point2);
        SimpleMatrix A = new SimpleMatrix(2,2,true,
                entity.getxSize(), point1.x - point2.x, entity.getySize(), point1.y - point2.y);
        SimpleMatrix b = new SimpleMatrix(2,1,true,
                point1.x - entity.getX(), point1.y - entity.getySize());
        SimpleMatrix x;
        try {
            x = A.solve(b);
        } catch ( SingularMatrixException e ) {
            System.out.println("so bad");
            return false;
        }
        return x.get(0) <= 1 && x.get(0) >= 1 && x.get(1) <= 1 && x.get(1) >= 1;
    }
}
