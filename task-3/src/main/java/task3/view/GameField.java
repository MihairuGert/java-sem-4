package task3.view;

import task3.entity.Movable;
import task3.entity.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GameField extends Scene {
    private ArrayList<Movable> movables;
    private ArrayList<Obstacle> obstacles;

    // Textures.
    private Image backgroundTexture;

    public GameField(Dimension screenSize) {
        super(screenSize);
        URL iconURL = getClass().getResource("/background.png");
        if (iconURL != null)
            backgroundTexture = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
    }

    public void setPlayers(ArrayList<Movable> players) {
        this.movables = players;
    }
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(backgroundTexture, 0,0,null);
        if (movables != null) {
            for (Movable movable : movables) {
                //graphics2D.drawRect(movable.getX(), movable.getY(), movable.getxSize(), movable.getySize());
                if (movable.getClass().getName().equals("task3.entity.Player")) {
                    AffineTransform oldTransform = graphics2D.getTransform();
                    graphics2D.rotate(Math.toRadians(movable.getRotationAngle()), movable.getX() + (double) movable.getxSize() / 2, movable.getY() + (double) movable.getySize() / 2);
                    graphics2D.drawImage(movable.getTexture(), movable.getX() - 5, movable.getY() - 5, null);
                    graphics2D.setTransform(oldTransform);
                } else if (movable.getClass().getName().equals("task3.entity.Undead")) {
                    graphics2D.drawImage(movable.getTexture(), movable.getX() - 5, movable.getY() - 5, null);
                    //graphics2D.drawRect(movable.getX(), movable.getY(), movable.getxSize(), movable.getySize());
                }
            }
        }
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                graphics2D.drawImage(obstacle.getTexture(), obstacle.getX(), obstacle.getY(), null);
                //graphics2D.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getxSize(), obstacle.getySize());
            }
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
