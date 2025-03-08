package task3.entity;

import task3.controller.Controller;

public class FastUndead extends Undead {

    public FastUndead() {
        super();
        super.setDifficultyClass(5);
    }

    public FastUndead(Controller AIController) {
        super(AIController);
        super.setVelocity(super.velocity * 2);
        super.setDifficultyClass(5);
    }
}
