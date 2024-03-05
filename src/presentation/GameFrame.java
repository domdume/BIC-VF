package presentation;

import javax.swing.*; // Provides components and classes for building GUIs (Windows, panels, buttons)

import presentation.GamePanel;

/**
 * Represents the top-level window in the Java GUI game.
 * This class extends JFrame, which provides the basic functionality to create a
 * window in a Java application
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Bad IceBlock Cream"); // this.add(new MainMenu());
        this.add(new GamePanel());
        this.pack(); // Adjusts the window to be large enough to contain the GamePanel
        this.setResizable(false); // The false argument does the window cannot be resized and will retain its initial size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application will close when the user closes the window
        this.setVisible(true);
        this.setLocationRelativeTo(null); // the true argument allows that the window appears in the center of the user's screen
    }
}