package presentation.graphics.menus;

import java.awt.*;

import presentation.graphics.views.View;
/**
 * The Modal class represents a translucent overlay used for displaying modal content on top of other views.
 * It extends the View class and provides functionality to create a modal with a specified transparency.
 */
public class Modal extends View {
    /**
     * Constructs a Modal instance with the specified transparency.
     *
     * @param alpha The transparency value for the modal.
     */
    public Modal(float alpha) {
        super(Color.BLACK);
        this.setViewTransparencyValue(alpha);
    }
}