package task3.weapon;

import task3.entity.Entity;
import task3.entity.Undead;

public class ZombieClaw extends Melee{
    public ZombieClaw() {
        range = 50;
    }

    @Override
    public Entity whoWasKilled() {
        Entity entity = super.whoWasKilled();
        if (entity == null) {
            return null;
        }
        if (entity instanceof Undead) {
            return null;
        }
        return entity;
    }
}
