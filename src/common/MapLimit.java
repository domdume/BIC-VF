package common;
import logic.levels.*;

/**
 * Provides map boundaries or dimensions based on the current game level
 */
public class MapLimit {
    public static final int MAP_ROW = 420;
    public static final int MAP_COLL = 420;
    private static Level level; // Static reference to the current level

    /**
     * The method makes use of the information of the currently active level
     *
     * @param currentLevel level number
     */
    public void setLevel(Level currentLevel) {
        level = currentLevel;
    }

    /**
     * Query current level to get map height
     * @return the height of the map and queries the current level
     */
    public int getMapHeight() {
        if (level != null) {
            return level.getNumberRows();
        }
        return 0; // Default or error handling
    }

    /**
     * Query current level to get map width
     * @return the width of the map if the level exists, otherwise returns zero
     */
    public int getMapWidth() {
        if (level != null) {
            return level.getNumberCols();
        }
        return 0; // Default or error handling
    }

}
