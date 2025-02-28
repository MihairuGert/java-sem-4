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
import java.util.Objects;

public class Scene extends JPanel {
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private final Dimension dimension;

    // Textures.
    private Image zombieTexture;
    private Image playerTexture;
    private int rotationAngle = 0;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
        dimension = screenSize;

        URL iconURL = getClass().getResource("/zombie.png");
        if (iconURL != null)
            zombieTexture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        iconURL = getClass().getResource("/player/range/player.png");
        if (iconURL != null)
            playerTexture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
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
        if (players != null) {
            for (Player player : players) {
                //graphics2D.drawRect(player.getX(), player.getY(), player.getxSize(), player.getySize());
                if (player.getClass().getName().equals("task3.entity.Player")) {
                    AffineTransform oldTransform = graphics2D.getTransform();
                    graphics2D.rotate(Math.toRadians(rotationAngle), player.getX() + (double)player.getxSize() / 2, player.getY() + (double)player.getySize() / 2);
                    graphics2D.drawImage(playerTexture, player.getX() - 5, player.getY() - 5, null);
                    graphics2D.setTransform(oldTransform);
                } else if (player.getClass().getName().equals("task3.entity.Undead")) {
                    graphics2D.drawImage(zombieTexture, player.getX() - 5, player.getY() - 5, null);
                }
            }
        }
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                graphics2D.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getxSize(), obstacle.getySize());
            }
        }
        rotationAngle += 1;
        if (rotationAngle >= 360) {
            rotationAngle = 0; // Сбрасываем угол после полного круга
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
