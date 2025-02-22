package task3.client;

import task3.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Scene extends JComponent implements MouseListener {

    // TODO player here is temporary decision.
    private Player player;

    Scene() {
        player = new Player();
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawOval(100,100,100,100);
    }

    void printMousePosition() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        System.out.println(point.x + " " + point.y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
