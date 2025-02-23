package task3.controller.commands;

import task3.server.ControllerCommand;

public class Command {
    protected char keyChar;
    protected boolean isKeyActive;
    protected ControllerCommand controllerCommand;

    public Command(ControllerCommand controllerCommand) {
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
