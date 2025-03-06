package task3.entity;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Obstacle extends Entity{
    public Obstacle(int x, int y, int xSize, int ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        URL iconURL = getClass().getResource("/wall0.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage();
        texture = texture.getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT);
    }

    public void setTexture() {
        URL iconURL = getClass().getResource("/wall0.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage();
        texture = texture.getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT);
    }
}
