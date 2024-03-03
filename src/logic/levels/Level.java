package logic.levels;

import common.Constant;

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

    public int getScore() {
        return this.score;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public int getLevelIndex() {
        return this.levelIndex;
    }

    // @Override
    // public int getEnemyCount() {
    // return this.enemyCount;
    // }

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
        int rows = Constant.SCREEN_WIDTH / Constant.UNIT_SIZE;
        int cols = Constant.SCREEN_HEIGHT / Constant.UNIT_SIZE;
        this.map = map.length != rows || map[0].length != cols ? new int[rows][cols] : map;
        for (int[] ints : map) {
            for (int anInt : ints) {
                this.score += anInt == 2 || anInt == 3 ? 1 : 0;
                this.enemyCount += anInt == 4 || anInt == 5 ? 1 : 0;
            }
        }
    }

}
