package task3.controller;

import task3.model.commands.player.ControllerCommand;

import java.io.Serializable;

public class Key implements Serializable {
    private static final long serialVersionUID = 1L;

    private char keyChar;
    private boolean isKeyActive;
    private ControllerCommand controllerCommand;

    public Key(ControllerCommand controllerCommand) {
        this.controllerCommand = controllerCommand;
    }

    public void setKeyActive() {
        isKeyActive = true;
    }
    public void setKeyNotActive() {
        isKeyActive = false;
    }
    public boolean isKeyChar(char keyChar) {
        return this.keyChar == keyChar;
    }
    public boolean isKeyActive() {
        return isKeyActive;
    }
    public ControllerCommand getName() {
        return controllerCommand;
    }
}
