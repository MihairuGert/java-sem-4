package task3.engine;

import task3.entity.FastUndead;
import task3.entity.Undead;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class WaveGenerator {
    private int numberOfWaves = 30;
    private int currentWave = 0;

    public WaveGenerator(int numberOfWaves) {
        this.numberOfWaves = numberOfWaves;

    }

    public HashMap<String, Integer> calculateNextWave() {
        currentWave++;
        if (currentWave > numberOfWaves) {
            return null;
        }
        HashMap<String, Integer> nextWave = new HashMap<>();
        try {
            nextWave.put("task3.entity.Undead", currentWave * Undead.class.getDeclaredConstructor().newInstance().getDifficultyClass());
            nextWave.put("task3.entity.FastUndead", currentWave * FastUndead.class.getDeclaredConstructor().newInstance().getDifficultyClass());
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }
        return nextWave;
    }

    public int getCurrentWave() {
        return currentWave;
    }
}
