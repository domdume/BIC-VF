package presentation.graphics.menus;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.Constant;
import presentation.graphics.views.View;

public class GameOverMenu extends View {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private Modal modal;
    private int score;
    private String msg;

    // Constructs the GameOverMenu with a white background, sets up the modal, and
    // creates buttons
    public GameOverMenu() {
        super(Color.WHITE);
        this.msg = "logic.common.Game Over";
        this.modal = new Modal(0.6f);
        this.modal.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.createButtons();
        modal.add(this);
    }

    // Creates buttons for the GameOverMenu and sets their positions and cursors
    private void createButtons() {
        this.button1 = new JButton("Restart");
        this.button1.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2 - 60, 100, 30);
        this.button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button1);

        this.button2 = new JButton("Play Again");
        this.button2.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2, 100, 30);
        this.button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button2);

        this.button3 = new JButton("Exit");
        this.button3.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2 + 60, 100, 30);
        this.button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button3);
    }

    // Sets the ActionListener for the "Restart" button
    public void setRestarListener(ActionListener listener) {
        this.button1.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Play Again" button.
     *
     * @param listener The ActionListener to be set.
     */
    // Sets the ActionListener for the "Play Again" button.
    public void setPlayAgainListener(ActionListener listener) {
        this.button2.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Exit" button.
     *
     * @param listener The ActionListener to be set.
     */
    // Sets the ActionListener for the "Exit" button
    public void setExitListener(ActionListener listener) {
        this.button3.addActionListener(listener);
    }

    /**
     * Overrides the paintComponent method to draw text on the panel.
     *
     * @param graphics The Graphics object to paint with.
     */
    // Overrides the paintComponent method to draw text on the panel.
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.sketcher.drawText(graphics, this, Color.RED, this.msg, Constant.SCREEN_WIDTH / 2,
                Constant.SCREEN_HEIGHT / 4);
        this.sketcher.drawText(graphics, this, Color.BLACK, "Score: " + score, Constant.SCREEN_WIDTH / 2,
                Constant.SCREEN_HEIGHT / 2 + 160);
    }

    /**
     * Updates the message and score of the game over menu.
     *
     * @param message The message to display.
     * @param score   The score to display.
     * @return The modal component.
     */
    // Updates the message and score of the game over menu
    public Component unwrap(String message, int score) {
        this.score = score;
        this.msg = message;
        return this.modal;
    }

    /**
     * Returns the modal component.
     * @return The modal component.
     */
    // Returns the modal component.
    @Override
    public Component unwrap() {
        return this.modal;
    }

}
