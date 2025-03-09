package task3.model;

import task3.entity.*;
import task3.model.commands.player.Movement;
import task3.sound.ShootSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class EventLoop implements ActionListener {
    private final Timer timer;
    private long tickCount = 0;
    private final int wavePeriod = 15;
    private final GameModel gameModel;
    private final int delay = 10;

    public EventLoop(GameModel gameModel) {
        this.gameModel = gameModel;
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tickCount++;
        ArrayList<String> sounds = new ArrayList<>();
        LinkedList<Entity> entities = new LinkedList<>();
        entities.addAll(gameModel.getMovables());
        entities.addAll(gameModel.getObstacles());
        for (Movable p : gameModel.getMovables()) {
            Movement.execute(p, p.getInput());
            Point point = p.getMousePoint();
            if (point != null && p instanceof Player) {
                ShootSound shootSound = new ShootSound();
                sounds.add(shootSound.getClass().getName());
                shootSound.playSound();
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
        gameModel.update(sounds);
        if (tickCount % (getSeconds() * wavePeriod) == 0) {
            gameModel.nextWave();
        }
    }

    private int getSeconds() {
        return 1000 / delay;
    }
}
