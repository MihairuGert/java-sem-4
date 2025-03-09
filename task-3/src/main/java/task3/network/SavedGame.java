package task3.network;

import task3.entity.Movable;
import task3.entity.Obstacle;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private final ArrayList<Movable> movables;
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<String> sounds;

    public SavedGame(ArrayList<Obstacle> obstacles, ArrayList<Movable> movables, ArrayList<String> sounds) {
        this.movables = movables;
        this.obstacles = obstacles;
        this.sounds = sounds;
    }

    public ArrayList<Movable> getMovables() {
        return movables;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<String> getSounds() {
        return sounds;
    }
}