package task3.model;

import task3.entity.*;
import task3.model.commands.player.Movement;
import task3.sound.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// TODO: Rename
public class EventLoop implements ActionListener {
    Timer timer;
    GameModel gameModel;

    public EventLoop(GameModel gameModel) {
        this.gameModel = gameModel;
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Entity> entities = new LinkedList<>();
        entities.addAll(gameModel.getMovables());
        entities.addAll(gameModel.getObstacles());
        for (Movable p : gameModel.getMovables()) {
            Movement.execute(p, p.getInput());
            Point point = p.getMousePoint();
            if (point != null && p.getClass().getName().equals("task3.entity.Player")) {
                SoundPlayer.playShoot();
            }
            for (Entity entity : entities) {
                if (p != entity) {
                    Collision.handleCollision(p, entity);
                    p.tryAttack(point, entity);
                }
            }
            Entity entity = p.whoWasKilled();
            if (entity != null) {
                entity.kill();
            }
        }
        gameModel.removeDead();
        if (gameModel.arePlayersDead()) {
            timer.stop();
            gameModel.endGame();
        }
        gameModel.update();
    }
}
