package presentation.graphics.menus;

import java.awt.*;
import presentation.graphics.views.View;

public class Modal extends View {

    /**
     * Constructs a Modal instance with the specified transparency.
     *
     * @param alpha The transparency value for the modal.
     */
    // Constructs a Modal instance with the specified transparency
    public Modal(float alpha) {
        super(Color.BLACK);
        this.setViewTransparencyValue(alpha);
    }

}
