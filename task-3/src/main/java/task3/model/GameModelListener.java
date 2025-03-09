package task3.model;

import task3.entity.Movable;
import task3.entity.Obstacle;

import java.awt.*;
import java.util.ArrayList;

public interface GameModelListener {
     void endGame();
     Dimension getScreenSize();
     void update(ArrayList<Movable> movables, ArrayList<Obstacle> obstacles, ArrayList<String> sounds);
}
