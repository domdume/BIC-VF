import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.JPanel;

import common.Constants;
import logic.Game;
import logic.entities.Entity;
import logic.levels.Level;

public class Guiless extends JPanel implements ActionListener {
    private Timer timer;
    private Game logic;
    private final KeyAdapter keyAdapter;
    private static final int DELAY = 250;

    /**
     * Constructs a Guiless object.
     * Initializes the panel layout, key adapter, game logic, and timer.
     * Starts the game and adds the key adapter to the panel.
     */
    public Guiless() {
        this.setLayout(null);
        this.setFocusable(true);
        this.keyAdapter = new CustomKeyAdapter();
        this.logic = new Game();
        this.timer = new Timer(DELAY, this);

        this.timer.start();
        logic.startGame();
        this.addKeyListener(this.keyAdapter);
    }

    /**
     * Paints the game components on the panel.
     * Displays game over or game completed messages if applicable.
     * Draws the game map if the game is running.
     * 
     * @param graphics The Graphics object used for painting.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (!logic.getAlive()) {
            System.out.println("Game Over - Score: " + logic.getTotalScore());
        }
        if (logic.getLastLevelCompleted()) {

            System.out.println("Game Completed - Score: " + logic.getTotalScore());
        }

        if (logic.getRunning()) {
            this.drawMap();
        }
    }

    /**
     * Handles the action performed event.
     * Updates the game logic, revalidates, and repaints the panel.
     * 
     * @param actionEvent The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.logic.actionPerformed();
        revalidate();
        repaint();
    }

    /**
     * Draws the game map by converting entities to a level representation and displaying it.
     */
    private void drawMap() {
        int rows = Constants.SCREEN_WIDTH / Constants.UNIT_SIZE;
        int cols = Constants.SCREEN_HEIGHT / Constants.UNIT_SIZE;
        Level level = this.entitiesToLevel(logic.getEntities(), rows, cols, logic.getScore(),
                logic.getCurrentLevelIndex());

        for (int i = 0; i < level.getMap().length; i++) {
            for (int j = 0; j < level.getMap()[i].length; j++) {
                System.out.print(level.getMap()[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("Score: " + logic.getTotalScore());
    }

    private class CustomKeyAdapter extends KeyAdapter {

        /**
         * Invoked when a key is pressed.
         * Handles the key press by notifying the game logic.
         * 
         * @param e The KeyEvent representing the key pressed.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            logic.handleGameKeys(e);
        }
    }

    /**
     * Gets the values of entities and updates the game map.
     * 
     * @param entity The entity to be updated on the game map.
     * @param map    The current game map.
     * @return The updated game map.
     */
    private int[][] getEntityValues(Entity entity, int[][] map) {
        int x = entity.getX() / Constants.UNIT_SIZE;
        int y = entity.getY() / Constants.UNIT_SIZE;
        int id = entity.getLevelId();
        map[x][y] = id;
        return map;
    }

    /**
     * Converts entities to a level representation.
     * 
     * @param entities    The list of entities in the game.
     * @param rows        The number of rows in the game map.
     * @param cols        The number of columns in the game map.
     * @param currentScore The current score of the game.
     * @param levelIndex  The index of the current game level.
     * @return The level representation of the game.
     */
    private Level entitiesToLevel(ArrayList<Entity> entities, int rows, int cols, int currentScore, int levelIndex) {
        int[][] map = new int[rows][cols];
        for (Entity entity : entities) {
            map = entity != null ? this.getEntityValues(entity, map) : map;
        }
        Level level = new Level();
        level.init(map, currentScore, levelIndex);
        return level;
    }
}
