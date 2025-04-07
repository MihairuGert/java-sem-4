package task3.engine;

import task3.model.GameModel;
import task3.entity.*;
import task3.engine.commands.player.Movement;
import task3.sound.ShootSound;
import task3.weapon.Weapon;

import javax.swing.*;
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
        for (Movable movable : gameModel.getMovables()) {
            Movement.execute(movable, movable.getInput());
            Point point = null;
            if (movable instanceof Attacking) {
                point = ((Attacking) movable).getMousePoint();
            }
            if (point != null && movable instanceof Player) {
                ShootSound shootSound = new ShootSound();
                sounds.add(shootSound.getClass().getName());
                shootSound.playSound();
            }
            Weapon weapon = null;
            if (movable instanceof Attacking) {
                weapon = ((Attacking) movable).getWeapon();
            }
            for (Entity entity : entities) {
                if (movable != entity) {
                    Collision.handleCollision(movable, entity);
                    if (point != null) {
                        tryAttack((Attacking) movable, weapon, point, entity);
                    }
                }
            }
            Entity entity = null;
            if (movable instanceof Attacking)
                entity = weapon.whoWasKilled();
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

    public void tryAttack(Attacking attacking, Weapon weapon, Point point, Entity entity) {
        if (point == null) {
            return;
        }
        weapon.isHit(new Point(attacking.getX() + attacking.getxSize() / 2, attacking.getY() + attacking.getySize() / 2), point, entity);
    }

    private int getSeconds() {
        return 1000 / delay;
    }
}
