package task3.view;

import task3.entity.Obstacle;
import task3.entity.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Scene extends JPanel {
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private HashMap<Dimension, Image> obstacleTextures;
    private final Dimension dimension;

    // Textures.
    private Image backgroundTexture;
    private Image zombieTexture;
    private Image playerTexture;
    private Image wallTexture;
    private int rotationAngle = 0;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
        dimension = screenSize;
        obstacleTextures = new HashMap<>();
        setDoubleBuffered(true);

        URL iconURL = getClass().getResource("/zombie.png");
        if (iconURL != null)
            zombieTexture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        iconURL = getClass().getResource("/player/range/player.png");
        if (iconURL != null)
            playerTexture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        iconURL = getClass().getResource("/background.png");
        if (iconURL != null)
            backgroundTexture = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
        iconURL = getClass().getResource("/wall0.png");
        if (iconURL != null)
            wallTexture = new ImageIcon(iconURL).getImage();
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
        graphics2D.drawImage(backgroundTexture, 0,0,null);
        if (players != null) {
            for (Player player : players) {
                graphics2D.drawRect(player.getX(), player.getY(), player.getxSize(), player.getySize());
                if (player.getClass().getName().equals("task3.entity.Player")) {
                    AffineTransform oldTransform = graphics2D.getTransform();
                    graphics2D.rotate(Math.toRadians(rotationAngle), player.getX() + (double)player.getxSize() / 2, player.getY() + (double)player.getySize() / 2);
                    graphics2D.drawImage(playerTexture, player.getX() - 5, player.getY() - 5, null);
                    graphics2D.setTransform(oldTransform);
                } else if (player.getClass().getName().equals("task3.entity.Undead")) {
                    graphics2D.drawImage(zombieTexture, player.getX() - 5, player.getY() - 5, null);
                    graphics2D.drawRect(player.getX(), player.getY(), player.getxSize(), player.getySize());
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
        rotationAngle += 1;
        if (rotationAngle >= 360) {
            rotationAngle = 0;
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
