package task3.network;

import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private final ArrayList<Movable> movables;
    private final ArrayList<Obstacle> obstacles;

    public SavedGame(ArrayList<Obstacle> obstacles, ArrayList<Movable> movables) {
        this.movables = movables;
        this.obstacles = obstacles;
    }

    public ArrayList<Movable> getMovables() {
        return movables;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
