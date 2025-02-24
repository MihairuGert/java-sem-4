package task3.view;

import task3.entity.Obstacle;
import task3.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Scene extends JPanel {
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private final Dimension dimension;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
        dimension = screenSize;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (Player player : players) {
            graphics2D.drawRect(player.getX(), player.getY(), player.getxSize(), player.getySize());
        }
        for (Obstacle obstacle : obstacles) {
            graphics2D.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getxSize(), obstacle.getySize());
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
