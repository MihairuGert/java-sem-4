package task3.entity;

import task3.engine.Point;
import task3.weapon.Melee;
import task3.weapon.Weapon;

public class Attacking extends Movable {
    public void setWeapon(Weapon weapon) {
        this.weapon = new Melee();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setActivePoint(Point activePoint) {
        if (this.activePoint == null) {
            this.activePoint = activePoint;
        }
    }

    private Point activePoint;

    protected Weapon weapon;

    Attacking() {
        super();
        weapon = new Weapon();
    }

    public Point getMousePoint() {
        Point point = activePoint;
        activePoint = null;
        return point;
    }
}
