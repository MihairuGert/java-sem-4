package task3.controller;

import task3.model.commands.player.ControllerCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PlayerController extends JPanel implements KeyListener, MouseListener, Controller {
    HashMap<String, Key> activeKeys;
    Point activePoint = null;
    Point lookPoint = null;

    public Point getLookPoint() {
        return lookPoint;
    }

    public PlayerController(Dimension screenSize) {
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
                lookPoint = e.getPoint();
            }
            @Override
            public void mouseDragged(MouseEvent e) {}
        });
    }

    @Override
    public LinkedList<ControllerCommand> getActiveCommands() {
        LinkedList<ControllerCommand> commands = new LinkedList<>();
        activeKeys.forEach((key, value) -> { if (value.isKeyActive()) commands.push(value.getName());});
        return commands;
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (activeKeys.containsKey(Character.toString(e.getKeyChar()))) {
            activeKeys.get(Character.toString(e.getKeyChar())).setKeyNotActive();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        activePoint = e.getPoint();
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
