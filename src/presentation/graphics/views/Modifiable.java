package presentation.graphics.views;

import java.awt.Component; // Fundamental class in AWT, it represents the components of the user interface (Buttons, text fields)
/**
 * Interface used to encapsulate the different graphical views of the user
 * interface and work with them uniformly through the common interface
 */
public interface Modifiable {
    /**
     * Gives access to manipulate and modificate the elements displayed on the
     * screen in the way required
     * 
     * @return Returns a Component object, which is an AWT graphical component
     */
    public Component unwrap();
}