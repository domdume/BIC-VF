package presentation.graphics.menus;

import presentation.Constant;
import presentation.graphics.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//import presentation.GameLogic;
public class MainMenu extends View {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private Modal modal;
    private String msg;

    /**
     * Constructs the MainMenu with a white background, sets up the modal, and creates buttons.
     */
    public MainMenu() {
        super(Color.white);
        this.msg = "Bad Ice Cream";
        this.modal = new Modal(0.6f);
        this.modal.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.createButtons();
        modal.add(this);
    }

     /**
     * Creates buttons for the MainMenu and sets their positions and cursors.
     */
    private void createButtons() {
        this.button1 = new JButton("PLAY");
        this.button1.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2 - 60, 100, 30);
        this.button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button1);

        this.button2 = new JButton("Load");
        this.button2.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2, 100, 30);
        this.button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button2);

        this.button3 = new JButton("Exit");
        this.button3.setBounds(Constant.SCREEN_WIDTH / 2 - 50, Constant.SCREEN_HEIGHT / 2 + 60, 100, 30);
        this.button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(button3);
    }

    /**
     * Sets the ActionListener for the "Start Game" button.
     *
     * @param listener The ActionListener to be set.
     */
    public void setStartGameListener(ActionListener listener) {

        this.button1.addActionListener(listener);
    }

    // Sets the ActionListener for the "Load Game" button
    public void setLoadGameListener(ActionListener listener) {

        this.button2.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Exit" button.
     *
     * @param listener The ActionListener to be set.
     */
    public void setExitListener(ActionListener listener) {
        this.button3.addActionListener(listener);
    }

    /**
     * Overrides the paintComponent method to draw text on the panel.
     *
     * @param graphics The Graphics object to paint with.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.sketcher.drawText(graphics, this, Color.RED, this.msg, Constant.SCREEN_WIDTH / 2,
                Constant.SCREEN_HEIGHT / 4);
    }

    /**
     * Returns the modal component.
     *
     * @return The modal component.
     */
    @Override
    public Component unwrap() {
        return super.unwrap();
    }
}
