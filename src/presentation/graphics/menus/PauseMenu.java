package presentation.graphics.menus;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

import presentation.Constant;
import presentation.graphics.views.View;

/**
 * The PauseMenu class represents the pause menu displayed during gameplay.
 * It extends the View class and provides functionality for pausing the game,
 * saving progress, loading saved games, and exiting the game.
 */
public class PauseMenu extends View {

    private JButton resumeBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton exitBtn;
    private Modal modal;

    /**
     * Constructs a PauseMenu instance with default settings.
     */
    public PauseMenu() {
        super(Color.WHITE);
        this.setViewTransparencyValue(0.8f);
        this.modal = new Modal(0.6f);
        this.modal.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.setBounds(Constant.MENU_X, Constant.MENU_Y, Constant.MENU_WIDTH, Constant.MENU_HEIGHT);
        this.createButtons();
        modal.add(this);
    }

    /**
     * Sets the action listener for the save button.
     *
     * @param listener The ActionListener to be set for the save button.
     */
    public void setSaveAL(ActionListener listener) {
        this.saveBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the load button.
     *
     * @param listener The ActionListener to be set for the load button.
     */
    public void setLoadAL(ActionListener listener) {
        this.loadBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the resume button.
     *
     * @param listener The ActionListener to be set for the resume button.
     */
    public void setResumeAL(ActionListener listener) {
        this.resumeBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the exit button.
     *
     * @param listener The ActionListener to be set for the exit button.
     */
    public void setExitAL(ActionListener listener) {
        this.exitBtn.addActionListener(listener);
    }

    /**
     * Creates and initializes the buttons for the PauseMenu.
     */
    private void createButtons() {
        this.saveBtn = new JButton("Save");
        this.saveBtn.setBounds(Constant.MENU_X - 50, 15, 100, 30);
        this.saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(saveBtn);

        this.loadBtn = new JButton("Load");
        this.loadBtn.setBounds(Constant.MENU_X - 50, Constant.MENU_Y - 45, 100, 30);
        this.loadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(loadBtn);

        this.resumeBtn = new JButton("Resume");
        this.resumeBtn.setBounds(Constant.MENU_X - 50, Constant.MENU_Y, 100, 30);
        this.resumeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(resumeBtn);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.setBounds(Constant.MENU_X - 50, Constant.MENU_Y + 45, 100, 30);
        this.exitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(exitBtn);
    }

    /**
     * Returns the modal component.
     *
     * @return The modal component.
     */
    @Override
    public Component unwrap() {
        return this.modal;
    }
}