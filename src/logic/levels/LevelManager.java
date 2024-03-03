package logic.levels;

import java.util.Map;
import java.util.HashMap;

public class LevelManager {
    public Level getLevel(int level) {
        return this.storage().getOrDefault(level, new Level1());
    }

    public int getMaxLevel() {
        return this.storage().size();
    }

    /**
     * Creates and returns a dictionary mapping integers to Level objects.
     * 
     * @return a dictionary mapping integers to Level objects.
     */
    private Map<Integer, Level> storage() {
        Map<Integer, Level> levelDictionary = new HashMap<>();
        levelDictionary.put(1, new Level1());
        levelDictionary.put(2, new Level2());
        levelDictionary.put(3, new Level3());
        return levelDictionary;
    }
}
