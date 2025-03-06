package task3.entity;

import task3.controller.Controller;
import task3.controller.PlayerController;
import task3.server.commands.player.ControllerCommand;
import task3.weapon.Melee;
import task3.weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.LinkedList;

public class Player extends Movable{
    private static final long serialVersionUID = 1L;

    public Player(Controller playerController) {
        super(playerController);
        URL iconURL = getClass().getResource("/player/range/player.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    }
    private void calculateAngle() {
        int playerCenterX = x + xSize / 2;
        int playerCenterY = y + ySize / 2;
        Point lookPoint = ((PlayerController)controller).getLookPoint();
        if (lookPoint == null) {
            rotationAngle = 0;
            return;
        }
        double norm = Math.hypot(lookPoint.y - playerCenterY, lookPoint.x - playerCenterX);
        if (lookPoint.x > playerCenterX) {
            rotationAngle = (Math.acos((double)(playerCenterY - lookPoint.y) / norm))*180/Math.PI;
        } else if (lookPoint.x < playerCenterX) {
            rotationAngle = (Math.acos((double)(lookPoint.y - playerCenterY) / norm))*180/Math.PI + 180;
        } else if (lookPoint.y >= playerCenterY) {
            rotationAngle = 0;
        } else {
            rotationAngle = 180;
        }
        //System.out.println(rotationAngle);
    }

    @Override
    public LinkedList<ControllerCommand> getInput() {
        LinkedList<ControllerCommand> linkedList = controller.getActiveCommands();
        calculateAngle();
        return linkedList;
    }
}
