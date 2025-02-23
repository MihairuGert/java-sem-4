package task3.controller;

public class Controller {
    private final char moveUp;
    private final char moveDown;
    private final char moveLeft;
    private final char moveRight;
    private final char shoot;

    public Controller(char moveUp, char moveDown, char moveRight, char moveLeft, char shoot) {
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.shoot = shoot;
    }

    public char getMoveUp() {
        return moveUp;
    }

    public char getMoveDown() {
        return moveDown;
    }

    public char getMoveLeft() {
        return moveLeft;
    }

    public char getMoveRight() {
        return moveRight;
    }

    public char getShoot() {
        return shoot;
    }
}
