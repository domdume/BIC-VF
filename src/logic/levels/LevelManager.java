package logic.levels;

import java.util.Map;
import java.util.HashMap;

public class LevelManager {
    /**
     * Retrieves the level corresponding to the provided index. If the requested level is not present, returns a new Level1() object by default.
     *
     * @param level The index of the level to retrieve.
     * @return A Level object corresponding to the provided index.
     */
    public Level getLevel(int level) {
        return this.storage().getOrDefault(level, new Level1());
    }

    /**
     * Returns the maximum number of available levels.
     *
     * @return The maximum number of available levels.
     */
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
        levelDictionary.put(4, new Level4());
        return levelDictionary;
    }
}
