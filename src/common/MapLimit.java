package common;
import logic.levels.*;

public class MapLimit {
    private static Level level; // Static reference to the current level

    /**
     * The method makes use of the information of the currently active level
     * @param currentLevel level number
     */
    public static void setLevel(Level currentLevel) {
        level = currentLevel;
    }

    public static int getMapHeight() {
        if (level != null) {
            return level.getNumberRows();
        }
        return 14; // Default or error handling
    }

    public static int getMapWidth() {
        if (level != null) {
            return level.getNumberCols();
        }
        return 14; // Default or error handling
    }

    public static final int MAP_WIDTH = getMapWidth();

    public static final int MAP_HEIGHT = getMapHeight();



















    public static final int MAP_ROW = 420;
    public static final int MAP_COLL = 420;
}
