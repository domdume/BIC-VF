package presentation.graphics.views;

import java.awt.*; // Provides classes and methods for working with graphics in Java (Such as Graphics and Rectangle)
import javax.swing.*; // Provides graphical and user interface components for Java applications (Such as JPanel)
import java.awt.event.*; // Provides classes and interfaces for working with events in AWT (such as ActionEvent)

import presentation.graphics.Sketcher; // Provides functionality for drawing in the view

/**
 * Represents a graphical view in the game. implements interfaces to handle
 * events and provides methods to set transparency and get the view as a graphical component. Additionally, it uses
 * a Sketcher object to draw in the view
 */
public class View extends JPanel implements ActionListener, Modifiable {
    public static final float OPAQUE_VIEW_VALUE = 1f;
    private float viewTransparencyValue; // Float that representes the view transparency value
    protected Sketcher sketcher; // Object Sketcher Used to draw in view
    protected Color viewBackGroundColor; // Object Color that represents the view background color

    /**
     * Constructor
     *
     * @param viewBackGroundColor represents the entered background color of the
     *                            view
     */
    public View(Color viewBackGroundColor) {
        this.viewBackGroundColor = viewBackGroundColor;
        this.viewTransparencyValue = OPAQUE_VIEW_VALUE;
        this.setLayout(null); // The null parameter indicates that the components in the view will have to be
        // positioned manually
        this.setFocusable(false); // Will not be able to respond to keyboard and mouse events
        this.setOpaque(false); // allows the content behind View to be visible
        this.sketcher = new Sketcher(this); // used to draw content in the view, that is why the view is passed as a
        // parameter
    }

    /**
     * Allows to set the transparency of the view
     *
     * @param viewTransparencyValue Float that representes the view transparency
     *                              value
     */
    protected void setViewTransparencyValue(float viewTransparencyValue) {
        this.viewTransparencyValue = viewTransparencyValue;
    }

    /**
     * This method is part of the ActionListener interface and is invoked when an
     * action event
     * occurs (such as clicking a button) and when that happens, the view is
     * validated and repainted
     *
     * @param actionEvent provides information about the action event that has
     *                    occurred
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Component class methods
        validate(); // Component sizes and positions are recalculated according to specified design
        // rules
        // Requests that the system repaint the component and its subcomponents. This is
        // useful when a change has been
        // made to the appearance or content of the component and the change needs to be
        // reflected visually on the screen
        repaint();
    }

    /**
     * It is called automatically every time the view needs to be repainted. The
     * drawBackground method
     * of the sketcher object is called to draw the background of the view
     *
     * @param graphics represents the graphical context in which the component and
     *                 its content are drawn
     */
    public void paintComponent(Graphics graphics) {
        // method of the parent class (JPanel in this case) to draw the background and
        // borders of the component. It
        // is important to ensure that the default paint behavior of the parent class is
        // maintained
        super.paintComponent(graphics);

        Rectangle bounds = getRectangle();
        // draws the background of the view
        this.sketcher.drawBackground(graphics, bounds, this.viewBackGroundColor, this.viewTransparencyValue);
    }

    /**
     * The rectangle will start in the top left corner of the component, and it
     * ensures that the rectangle
     * encompasses all the available space of the component it is being drawn on
     *
     * @return object Rectangle that represents the limits of the component
     */
    private Rectangle getRectangle() {
        return new Rectangle(0, 0, getBounds().width, getBounds().height);
    }

    /**
     * Method of the Modifiable interface, it is used to pass an instance of a class
     * such as a graphical
     * component to another method or class that requires a graphical component
     *
     * @return returns the current View as a graphical component
     */
    @Override
    public Component unwrap() {
        return this;
    }
}