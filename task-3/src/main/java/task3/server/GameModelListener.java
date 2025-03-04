package task3.server;

import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public interface GameModelListener {
     void endGame();
     Dimension getScreenSize();
     void update(ArrayList<Movable> movables, ArrayList<Obstacle> obstacles);
}
