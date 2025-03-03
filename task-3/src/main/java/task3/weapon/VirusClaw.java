package task3.weapon;

import task3.entity.Entity;

public class VirusClaw extends Melee{
    public VirusClaw() {
        range = 50;
    }

    @Override
    public Entity whoWasKilled() {
        Entity entity = super.whoWasKilled();
        if (entity == null) {
            return null;
        }
        if (entity.getClass().getName().equals("task3.entity.Undead")) {
            return null;
        }
        return entity;
    }
}
