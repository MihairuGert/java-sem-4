package task3.view;

import task3.controller.Controller;
import task3.entity.Entity;
import task3.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Scene extends JPanel implements MouseListener, ActionListener {
    private ArrayList<Player> players;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
    }

    public void getPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Отрисовка всех игроков
        for (Player player : players) {
            graphics2D.drawOval(player.getX() - 30, player.getY() - 50, 50, 50);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
