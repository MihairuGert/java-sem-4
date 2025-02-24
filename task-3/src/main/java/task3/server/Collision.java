package task3.server;

import task3.entity.Entity;

public class Collision {
    private static boolean isColliding(Entity entity1, Entity entity2) {
        return entity1.getX() < entity2.getX() + entity2.getxSize() &&
                entity1.getX() + entity1.getxSize() > entity2.getX() &&
                entity1.getY() < entity2.getY() + entity2.getySize() &&
                entity1.getY() + entity1.getySize() > entity2.getY();
    }
    private static void resolveCollision(Entity entity1, Entity entity2) {
        float overlapX = Math.min(
                entity1.getX() + entity1.getxSize() - entity2.getX(),
                entity2.getX() + entity2.getxSize() - entity1.getX()
        );
        float overlapY = Math.min(
                entity1.getY() + entity1.getySize() - entity2.getY(),
                entity2.getY() + entity2.getySize() - entity1.getY()
        );

        if (overlapX < overlapY) {
            if (entity1.getX() < entity2.getX()) {
                entity1.move(entity2.getX() - entity1.getxSize(), entity1.getY());
            } else {
                entity1.move(entity2.getX() + entity2.getxSize(), entity1.getY());
            }
        } else {
            if (entity1.getY() < entity2.getY()) {
                entity1.move(entity1.getX(), entity2.getY() - entity1.getySize());
            } else {
                entity1.move(entity1.getX(), entity2.getY() + entity2.getySize());
            }
        }
    }
    public static void handleCollision(Entity entity1, Entity entity2) {
        if (isColliding(entity1, entity2)) {
            resolveCollision(entity1, entity2);
        }
    }
}
