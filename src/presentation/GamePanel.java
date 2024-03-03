package presentation;//package src;

import java.awt.*; // Contains classes and methods for creating and manipulating user interface components (such as windows, buttons, graphics)
import javax.swing.*; // Provides components and classes for building GUIs (Windows, panels, buttons)
import data.FileHandler;

import java.awt.event.ActionEvent; // This class represents an action event that is generated when an action is performed on a graphical interface component
//Representa un evento de acción que se genera cuando se realiza una acción en un componente de la interfaz gráfica.
import java.awt.event.ActionListener; // This Interface defines the method actionPerformed(ActionEvent e) which is implemented by classes that wish to listen to action events.
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logic.Game;
import presentation.graphics.Sketcher;
import presentation.graphics.menus.GameOverMenu;
import presentation.graphics.menus.MainMenu;
import presentation.graphics.menus.PauseMenu;

/**
 * The GamePanel class is responsible for managing the game's graphical user
 * interface (GUI) and game logic. It extends
 * the JPanel class, which is a lightweight container used for organizing and
 * displaying components.
 * It implements the ActionListener interface, which allows it to receive action
 * events from components (Like buttons).
 */
public class GamePanel extends JPanel implements ActionListener {
    // Declaration
    private Timer timer; // Used to update the game at regular intervals
    private static final int DELAY = 250; // Constant representing the delay in milliseconds between game updates
    private Game logic;
    private Sketcher sketcher; // Used to draw the game's entities and score on the screen
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private PauseMenu pauseMenu;
    private final KeyAdapter keyAdapter;
    private FileHandler fileHandler;
    protected boolean activePauseMenu;

    /**
     * The constructor initializes the game's components and sets up the game's
     * timer.
     */
    public GamePanel() {
        //
        this.setLayout(null); // Components within GamePanel will be able to be positioned using coordinates
                              // (They will not be subject to any specific layout manager)
        this.setBackground(Color.WHITE);
        this.setFocusable(true); // Will be able to respond to keyboard and mouse events when active
        this.setPreferredSize(new Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));

        this.mainMenu = new MainMenu();
        this.pauseMenu = new PauseMenu();
        this.gameOverMenu = new GameOverMenu();
        this.keyAdapter = new CustomKeyAdapter();
        this.fileHandler = new FileHandler();

        this.timer = new Timer(DELAY, this); // Timer instance with a defined delay and sets the GamePanel as the object
                                             // that listens to the timer's action events.
        this.logic = new Game();
        this.sketcher = new Sketcher(this);
        this.setExternalListeners();
        showMainMenu();
    }

    /**
     * Sets the action listeners for various buttons in the menus.
     */
    public void setExternalListeners() {
        this.pauseMenu.setResumeAL(e -> hideMenu());

        this.pauseMenu.setExitAL(e -> exitGame());

        this.pauseMenu.setSaveAL(e -> saveGame());

        this.pauseMenu.setLoadAL(e -> loadGame());

        this.gameOverMenu.setRestarListener(e -> restartListener());

        this.gameOverMenu.setPlayAgainListener(e -> playAgainListener());

        // this.mainMenu.setRestarListener(e-> exitGame());
        this.mainMenu.setStartGameListener(e -> startGameListener());
        this.mainMenu.setExitListener(e -> exitGame());
        this.gameOverMenu.setExitListener(e -> exitGame());
    }

    /**
     * Action listener for the "Play Again" button.
     */
    private void playAgainListener() {
        logic.resetLevel();
        this.removeKeyListener(this.keyAdapter);
        logic.restartGame();
        this.addKeyListener(this.keyAdapter);
    }

    /**
     * Action listener for the "Restart" button.
     */
    private void restartListener() {
        logic.resetToLevel1();
        this.removeKeyListener(this.keyAdapter);
        logic.restartGame();
        this.addKeyListener(this.keyAdapter);
    }

    /**
     * Action listener for the "Start Game" button.
     */
    private void startGameListener() {
        this.remove(mainMenu);
        this.timer.start();
        logic.startGame();
        this.addKeyListener(this.keyAdapter);
    }

    /**
     * Adds the game over menu with a specific message.
     * 
     * @param msg The message to display.
     * @return An integer value (0).
     */
    private int addGameOver(String msg) {
        this.add(this.gameOverMenu.unwrap(msg, logic.getTotalScore()));
        return 0;
    }

    /**
     * Saves the current game state.
     */
    private void saveGame() {
        this.fileHandler.saveEntityFile(logic.getEntities(), logic.getScore(), logic.getCurrentLevelIndex());
        this.requestFocus();
    }

    /**
     * Loads a saved game state.
     */
    private void loadGame() {
        logic.generateEntities(this.fileHandler.loadEntityFile(), 0);
        this.requestFocus();
    }

    /**
     * Handles the menu display based on key events.
     * 
     * @param e The KeyEvent representing the key pressed.
     * @return An integer value based on menu visibility.
     */
    private int handleMenu(KeyEvent e) {
        return !activePauseMenu && e.getKeyCode() == KeyEvent.VK_ESCAPE && logic.getRunning() ? showMenu() : hideMenu();
    }

    /**
     * Displays the pause menu.
     * 
     * @return An integer value (0).
     */
    private int showMenu() {
        this.activePauseMenu = true;
        this.add(this.pauseMenu.unwrap());
        return 0;
    }

    /**
     * Displays the main menu.
     * 
     * @return An integer value (0).
     */
    private int showMainMenu() {
        this.add(this.mainMenu.unwrap());
        return 0;
    }

    /**
     * Hides the pause menu.
     * 
     * @return An integer value (1).
     */
    private int hideMenu() {
        this.activePauseMenu = false;
        this.remove(this.pauseMenu.unwrap());
        this.requestFocus();
        return 1;
    }

    /**
     * Exits the game.
     */
    private void exitGame() {
        this.timer.stop();
        System.out.println("HERE EXIT");
        Container frame = this.getParent();
        do
            frame = frame.getParent();
        while (!(frame instanceof JFrame));
        ((JFrame) frame).dispose();
    }

    /**
     * Draws the graphical components on the panel
     * 
     * @param graphics represents the graphical context in which the drawings will
     *                 be made in the component
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // Ensures that the component is painted correctly before performing any other
                                        // drawing operations
        draw(graphics); // Contains the logic to draw game elements in the graphical context provided by
                        // g
    }

    /**
     * Checks if the game is running to call the sketch(g) method
     * 
     * @param graphics is the graphical context that provides the drawing
     *                 capabilities necessary to perform drawing operations
     * @return Returns the sketch(g) call and this returns 0, otherwise returns 1
     */
    private int draw(Graphics graphics) {
        return logic.getRunning() ? this.sketch(graphics)
                : !logic.getAlive() ? this.addGameOver("Game Over")
                        : logic.getLastLevelCompleted() ? this.addGameOver("Game Completed") : 1;
    }

    /**
     * Draws the game entities and score on the GamePanel. Call sketcher methods to
     * make
     * the drawings and then remove the gameOverMenu component from the GamePanel.
     * 
     * @param graphics performs drawing operations in the graphical context provided
     *                 by the Java window system (This
     *                 allows the method to draw game elements)
     * @return 0 int value to not use if conditional
     */
    private int sketch(Graphics graphics) {
        this.sketcher.drawEntities(graphics, this.logic.getEntities());
        this.sketcher.drawScore(graphics, this.logic.getTotalScore());
        this.remove(gameOverMenu.unwrap());
        return 0;
    }

    /**
     * Is called every time an action event occurs, in this case, every time the
     * timer is activated
     * 
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.logic.actionPerformed(); // Para realizar las acciones correspondientes al juego
        revalidate(); // Updates the game panel
        repaint(); // Updates the game panel
    }

    private class CustomKeyAdapter extends KeyAdapter {
        
        /**
         * Invoked when a key is pressed. 
         * It triggers methods to handle game actions and menu visibility based on the key event.
         * 
         * @param e The KeyEvent representing the key pressed.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            logic.handleGameKeys(e);
            handleMenu(e);
        }
    }
}
