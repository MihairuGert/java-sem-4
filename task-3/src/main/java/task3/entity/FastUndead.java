package task3.entity;

public class FastUndead extends Undead {

    public FastUndead() {
        super();
        super.setVelocity(super.velocity * 2);
        super.setDifficultyClass(5);
    }
}
