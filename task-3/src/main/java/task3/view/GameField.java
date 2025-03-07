package task3.view;

import task3.entity.*;

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
    private HashMap<String, Image> textures;

    private HashMap<Dimension, Image> obstacleTextures;
    private Image wallTexture;

    // Textures.
    private Image backgroundTexture;

    private void getTextures() {
        textures = new HashMap<>();
        obstacleTextures = new HashMap<>();
        Image texture = null;
        URL iconURL = getClass().getResource("/player/range/player.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        textures.put("task3.entity.Player", texture);

        iconURL = getClass().getResource("/zombie.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        textures.put("task3.entity.Undead", texture);

        iconURL = getClass().getResource("/wall0.png");
        if (iconURL != null)
            wallTexture = new ImageIcon(iconURL).getImage();
    }

    public GameField(Dimension screenSize) {
        super(screenSize);
        // todo make sth better
        getTextures();
        setDoubleBuffered(true);
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
        graphics2D.drawImage(backgroundTexture, 0,0,this);
        if (movables != null) {
            for (Movable movable : movables) {
                //graphics2D.drawRect(movable.getX(), movable.getY(), movable.getxSize(), movable.getySize());
                if (movable instanceof Player) {
                    AffineTransform oldTransform = graphics2D.getTransform();
                    graphics2D.rotate(Math.toRadians(movable.getRotationAngle()), movable.getX() + (double) movable.getxSize() / 2, movable.getY() + (double) movable.getySize() / 2);
                    graphics2D.drawImage(textures.get(movable.getClass().getName()), movable.getX() - 5, movable.getY() - 5, this);
                    graphics2D.setTransform(oldTransform);
                } else if (movable instanceof Undead) {
                    graphics2D.drawImage(textures.get(movable.getClass().getName()), movable.getX() - 5, movable.getY() - 5, this);
                    //graphics2D.drawRect(movable.getX(), movable.getY(), movable.getxSize(), movable.getySize());
                }
            }
        }
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                Dimension size = new Dimension(obstacle.getxSize(), obstacle.getySize());
                Image scaledTexture = obstacleTextures.get(size);
                if (scaledTexture == null) {
                    scaledTexture = wallTexture.getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT);
                    obstacleTextures.put(size, scaledTexture);
                }
                graphics2D.drawImage(scaledTexture, obstacle.getX(), obstacle.getY(), null);
                graphics2D.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getxSize(), obstacle.getySize());
            }
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
