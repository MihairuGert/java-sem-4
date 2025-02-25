package task3.controller;

import task3.server.commands.player.ControllerCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PlayerController extends JPanel implements KeyListener, MouseListener, Controller {
    HashMap<String, Key> activeKeys;
    Point activePoint;

    public PlayerController(Dimension screenSize) {
        InputStream inputStream = PlayerController.class.getResourceAsStream("/keybindings.properties");
        Properties properties = new Properties();
        activeKeys = new HashMap<>();
        activePoint = new Point();
        try {
            properties.load(inputStream);
            for (String keyChar : properties.stringPropertyNames()) {
                String commandName = properties.getProperty(keyChar);
                activeKeys.put(keyChar, new Key(ControllerCommand.valueOf(commandName)));
            }
        } catch (IOException e) {
            System.out.println("Unable to load keybindings.");
        }
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(screenSize);
    }

    public void print() {
        activeKeys.forEach((key, value) -> System.out.println(key + " " + value.getName()));
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
        activePoint = MouseInfo.getPointerInfo().getLocation();
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
