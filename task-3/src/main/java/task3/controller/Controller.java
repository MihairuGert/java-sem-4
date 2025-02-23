package task3.controller;

import task3.controller.commands.Command;
import task3.server.ControllerCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Controller implements KeyListener, MouseListener {
    HashMap<String, Command> keyBindings = new HashMap<>();

    public Controller() {
        InputStream inputStream = Controller.class.getResourceAsStream("/keybindings.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            for (String keyChar : properties.stringPropertyNames()) {
                String commandName = properties.getProperty(keyChar);
                keyBindings.put(keyChar, new Command(ControllerCommand.valueOf(commandName)));
            }
        } catch (IOException e) {
            System.out.println("Unable to load keybindings.");
        }
    }

    public void print() {
        keyBindings.forEach((key, value) -> System.out.println(key + " " + value.getName()));
    }

    public LinkedList<ControllerCommand> getActiveCommands() {
        LinkedList<ControllerCommand> commands = new LinkedList<>();
        keyBindings.forEach((key, value) -> { if (value.isKeyActive()) commands.push(value.getName());});
        return commands;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyBindings.containsKey(Character.toString(e.getKeyChar()))) {
            keyBindings.get(Character.toString(e.getKeyChar())).setKeyActive();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyBindings.containsKey(Character.toString(e.getKeyChar()))) {
            keyBindings.get(Character.toString(e.getKeyChar())).setKeyNotActive();
        }
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
