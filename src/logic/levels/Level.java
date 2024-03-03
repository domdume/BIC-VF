package logic.levels;

public class Level {

    protected int score;
    protected int[][] map;
    protected int levelIndex;
    protected int enemyCount;
    protected int currentScore;

    /**
     * Initializes the game state with the provided map, current score, and level
     * index.
     *
     * @param map          an integer type of map. The map representing the game
     *                     layout.
     * @param currentScore currentScore an integer representing the current score of
     *                     the player.
     * @param levelIndex   an integer representing the index of the current level.
     */
    public void init(int[][] map, int currentScore, int levelIndex) {
        this.score = currentScore;
        this.currentScore = currentScore;
        this.levelIndex = levelIndex;
        this.setMap(map);
    }

    /**
     * Retrieves the total score accumulated in the level.
     * 
     * @return the total score accumulated in the level
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Retrieves the current score of the player.
     * 
     * @return the current score of the player
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Retrieves the index of the current level.
     * 
     * @return the index of the current level
     */
    public int getLevelIndex() {
        return this.levelIndex;
    }

    /**
     * Gets the number of rows in the map.
     *
     * @return The number of rows in the map, or 0 if the map is null.
     */
    public int getNumberRows() {
        return this.map != null ? this.map.length : 0;
    }

    /**
     * Gets the number of columns in the map.
     *
     * @return The number of columns in the map, or 0 if the map is null or has no
     *         rows.
     */
    public int getNumberCols() {
        return this.map != null && this.map.length > 0 ? this.map[0].length : 0;
    }

    public int[][] getMap() {
        return this.map;
    }

    /**
     * Sets the map for the game, and calculates scores and enemy count based on the
     * map.
     *
     * @param map an integer type of map. The map representing the game layout.
     */
    private void setMap(int[][] map) {
        this.map = map;
        if (map != null) {
            for (int[] ints : map) {
                for (int tileValue  : ints) {
                    this.score += tileValue  == 2 || tileValue  == 3 ? 1 : 0;
                    this.enemyCount += tileValue  == 4 || tileValue  == 5 ? 1 : 0;
                }
            }
        }
    }

}
