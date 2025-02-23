package task3.view;

import task3.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Scene extends JPanel implements MouseListener, ActionListener {

    // TODO player here is temporary decision.
    private Player player;
    Timer timer;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
        player = new Player();
        timer = new Timer(1, this);
        this.addMouseListener(this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawOval(player.getX() - 30, player.getY() - 50, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        System.out.println(point.x + " " + point.y);
        player.move(point.x, point.y);
        repaint();
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
