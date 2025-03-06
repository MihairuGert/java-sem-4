package task3.network;

import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Movable> movables = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    public SavedGame(ArrayList<Player> players, ArrayList<Obstacle> obstacles, ArrayList<Movable> movables) {
        this.players = players;
        this.movables = movables;
        this.obstacles = obstacles;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Movable> getMovables() {
        return movables;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
