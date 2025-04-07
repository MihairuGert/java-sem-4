package task3.controller;

import task3.engine.commands.player.ControllerCommand;
import task3.entity.Player;
import task3.engine.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PlayerController extends JPanel implements KeyListener, MouseListener, Controller {
    private final HashMap<String, Key> activeKeys;
    private Point activePoint = null;
    private Point lookPoint = null;
    private final Player player;

    public Point getLookPoint() {
        return lookPoint;
    }

    public PlayerController(Dimension screenSize, Player player) {
        InputStream inputStream = PlayerController.class.getResourceAsStream("/keybindings.properties");
        Properties properties = new Properties();
        activeKeys = new HashMap<>();
        try {
            properties.load(inputStream);
            for (String keyChar : properties.stringPropertyNames()) {
                String commandName = properties.getProperty(keyChar);
                activeKeys.put(keyChar, new Key(ControllerCommand.valueOf(commandName)));
            }
        } catch (IOException e) {
            System.err.println("Unable to load keybindings.");
        }
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(screenSize);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                java.awt.Point point = e.getPoint();
                lookPoint = new Point(point.x, point.y);
                player.setLookPoint(lookPoint);
            }
            @Override
            public void mouseDragged(MouseEvent e) {}
        });
        this.player = player;
    }

    @Override
    public void setActiveCommands() {
        LinkedList<ControllerCommand> commands = new LinkedList<>();
        activeKeys.forEach((key, value) -> { if (value.isKeyActive()) commands.push(value.getName());});
        player.setActiveCommands(commands);
    }
    public Point getPoint() {
        return activePoint;
    }

    @Override
    public void setPointNull() {
        activePoint = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (activeKeys.containsKey(Character.toString(e.getKeyChar()))) {
            activeKeys.get(Character.toString(e.getKeyChar())).setKeyActive();
        }
        setActiveCommands();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (activeKeys.containsKey(Character.toString(e.getKeyChar()))) {
            activeKeys.get(Character.toString(e.getKeyChar())).setKeyNotActive();
        }
        setActiveCommands();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        java.awt.Point point = e.getPoint();
        activePoint = new Point(point.x, point.y);
        player.setActivePoint(activePoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        activePoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
