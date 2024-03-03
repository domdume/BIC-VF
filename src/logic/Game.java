package logic;

import java.util.ArrayList;
import java.awt.event.KeyEvent;

import logic.entities.Entity;
import logic.entities.IceCream;
import logic.generators.EntityGenerator;
import logic.levels.Level;
import logic.levels.LevelManager;
import logic.results.Death;
import logic.results.Points;

public class Game {

    private int score;
    private int totalScore;
    private boolean running;
    private Level currentLevel;
    private int currentLevelIndex;
    private LevelManager levelManager;
    private ArrayList<Entity> entities;
    private boolean lastLevelCompleted;
    private boolean alive;

    public Game() {
        this.totalScore = 0;
        this.running = false;
        this.currentLevelIndex = 1;
        this.levelManager = new LevelManager();
        this.lastLevelCompleted = false;
        this.alive = true;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public boolean getLastLevelCompleted() {
        return this.lastLevelCompleted;
    }

    public boolean getAlive() {
        return this.alive;
    }

    private boolean setAlive(boolean val) {
        this.alive = val;
        return val;
    }

    private boolean setLastLevelCompleted(boolean val) {
        this.lastLevelCompleted = !val;
        return val;
    }

    public int getScore() {
        return this.score;
    }

    public int getCurrentLevelIndex() {
        return this.currentLevelIndex;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public boolean getRunning() {
        return this.running;
    }

    public void resetToLevel1() {
        this.currentLevelIndex = 1;
        this.totalScore = 0;
    }

    public void resetLevel() {
        currentLevelIndex -= currentLevelIndex > levelManager.getMaxLevel() ? 1 : 0;
        totalScore -= score;
    }

    public int moveEntities() {
        int moveResult  = 0;
        for (Entity entity : this.entities) {
            moveResult  = entity != null ? entity.move(this.entities) : 1;
        }
        return moveResult ;
    }

    public void startGame() {
        this.running = true;
        this.generateEntities(this.levelManager.getLevel(this.currentLevelIndex), this.totalScore);
    }

    public int restartGame() {
        this.entities = new ArrayList<Entity>();
        this.startGame();
        return 0;
    }

    private int handleFoodCollition(int foodIndex) {
        this.score++;
        this.totalScore++;
        this.entities.set(foodIndex, null);
        return 0;
    }

    private void setRunning(boolean val) {
        this.running = val;
    }

    private int handlePlayerCollitions(Entity entity) {
        int handleResult  = 0;
        boolean isRunning = this.running;
        for (int i = 0; i < this.entities.size() && isRunning; i++) {
            handleResult  = entity.handleCoalitions(this.entities.get(i)) instanceof Points
                    ? this.handleFoodCollition(i)
                    : 1;
            isRunning = (entity.handleCoalitions(this.entities.get(i)) instanceof Death)
                    ? this.setAlive(false)
                    : true;
        }
        this.setRunning(isRunning);
        return handleResult ;
    }

    private int adjustLevel() {
        this.currentLevelIndex++;
        this.currentLevelIndex = this.currentLevelIndex > this.levelManager.getMaxLevel()
                ? this.levelManager.getMaxLevel() + 1
                : this.currentLevelIndex;
        int levelAdjusted  = this.currentLevelIndex <= this.levelManager.getMaxLevel() ? this.restartGame() : 1;
        return levelAdjusted ;
    }

    public int checkScore() {
        int scoreAdjusted  = this.score == this.currentLevel.getScore() ? this.adjustLevel() : 1;
        boolean isRunning = this.getRunning();
        // This is doing nothing but replacing a simple if .-.
        while (isRunning) {
            isRunning = this.currentLevelIndex > this.levelManager.getMaxLevel()
                    ? this.setLastLevelCompleted(false)
                    : true;
            break;
        }
        this.setRunning(isRunning);
        return scoreAdjusted ;
    }

    public int checkCollitions() {
        int collisionsChecked  = 0;
        for (Entity entity : this.entities) {
            collisionsChecked  = entity != null && entity instanceof IceCream ? this.handlePlayerCollitions(entity) : 1;
        }
        return collisionsChecked ;
    }

    public void generateEntities(Level level, int score) {
        this.currentLevel = level;
        this.currentLevelIndex = this.currentLevel.getLevelIndex();
        this.score = this.currentLevel.getCurrentScore();
        this.totalScore = this.score + score;
        this.entities = EntityGenerator.generate(this.currentLevel);
    }

    private int handleRunningAction() {
        this.checkCollitions();
        this.moveEntities();
        this.checkScore();
        return 0;
    }

    public int actionPerformed() {
        return this.getRunning() ? this.handleRunningAction() : 0;
    }

    public int handleGameKeys(KeyEvent e) {
        int keyHandlingResult  = 0;
        for (Entity entity : entities) {
            keyHandlingResult  = entity != null ? entity.handleKeyEvent(e, entities) : 1;
        }
        return keyHandlingResult ;
    }

}
