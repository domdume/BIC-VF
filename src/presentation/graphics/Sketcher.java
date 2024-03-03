package presentation.graphics;

import java.awt.*; // Provides classes for creating and manipulating graphics in Java (Graphics, Graphics2D, Color, Font)
import javax.swing.*; // Provides components and classes for building GUIs (Windows, panels, buttons)
import java.io.IOException; // This class handles data input/output exception
import java.util.ArrayList;
import javax.imageio.ImageIO; // This class read and write images in different formats
import java.awt.image.BufferedImage; // This class represents an image saved in memory

import common.Constants;
import logic.entities.Entity;

/**
 * The class is for drawing graphics in the Java GUI game. Provides methods for
 * drawing the components of the game (entities,
 * scores, and backgrounds. It can also draw text on the screen)
 */
public class Sketcher {

    private JPanel panel; // Saves the panel on which graphic elements are drawn.

    /**
     * The constructor takes a panel as a parameter and assigns it to the attribute
     * 
     * @param enteredPanel used to set any panel on which graphic elements will be
     *                     drawn
     */
    public Sketcher(JPanel enteredPanel) {
        this.panel = enteredPanel;
    }

    /**
     * Iterates through the list of entities and draws each one by calling the
     * drawImage method
     * 
     * @param graphics This graphics context allows the drawEntities method to draw
     *                 the entities in the appropriate panel of the application
     * @param entities represents each individual entity found in the list
     * @return The method returns 0 if all entities are drawn correctly and 1 if
     *         there is at least one entity that cannot be drawn
     */
    public int drawEntities(Graphics graphics, ArrayList<Entity> entities) {
        int tmp = 0;
        for (Entity entity : entities) {
            tmp = entity != null ? this.drawImage(this.panel, graphics, entity) : 1;
        }
        return tmp;
    }

    /**
     * Draws the score in the graphical context specified by graphics.
     * 
     * @param graphics graphic context in which the drawing is made
     * @param points   represents the score that will be drawn in the graphical
     *                 context
     */
    public void drawScore(Graphics graphics, int points) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Drawing font metrics are obtained, including information about the size and
        // position of characters in the font
        FontMetrics metrics = this.panel.getFontMetrics(graphics.getFont());
        String score = "Score: " + points;
        // Draw the scoring chain in the graphical context (String - coordinates - size)
        graphics.drawString(score, placeTheTextAtTheTopRightOfTheScreen(metrics, score), getSizeOfTheFont(graphics));
    }

    /**
     * gets the size of the font used
     * 
     * @param graphics graphic context from which the font size is obtained
     * @return Returns an integer of the size of the font currently configured on
     *         the graphic context
     */
    private static int getSizeOfTheFont(Graphics graphics) {
        return graphics.getFont().getSize();
    }

    /**
     * Calculates the x position where the text should be drawn at the top right of
     * the screen
     * 
     * @param metrics Object FontMetrics used to measure properties of the source
     * @param score   String representing the score to be displayed on the screen
     * @return returns an integer representing the X coordinate
     */
    private static int placeTheTextAtTheTopRightOfTheScreen(FontMetrics metrics, String score) {
        return Constants.SCREEN_WIDTH - metrics.stringWidth(score) - Constants.UNIT_SIZE * 2;
    }

    /**
     * Draws an image associated with an entity in a graphical context provided by
     * graphics
     * 
     * @param panelParent JPanel that represents the panel on which the image is
     *                    drawn
     * @param graphics    Graphics that represents the graphic context in which the
     *                    drawing is made
     * @param entity      Entity object that contains information about the image to
     *                    be drawn and its position on the panel
     * @return 0 for the ternary operator
     */
    private int drawImage(JPanel panelParent, Graphics graphics, Entity entity) {
        try {
            BufferedImage entityImage = ImageIO.read(panelParent.getClass().getResource(entity.getImagePath()));
            graphics.drawImage(entityImage, entity.getX(), entity.getY(), panelParent);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return 0;
    }

    /**
     * Draws a rectangular background with a specific color and transparency in the
     * graphic context provided by graphics
     * 
     * @param graphics                    Object Graphics that represents the
     *                                    graphic context in which the drawing will
     *                                    be made
     * @param bounds                      Object Rectangle that represents the
     *                                    boundaries of the rectangular background
     *                                    to be drawn
     * @param backGroundColor             Color object that represents the color of
     *                                    the background
     * @param transparencyBackGroundValue float that represents the transparency of
     *                                    the background
     */
    public void drawBackground(Graphics graphics, Rectangle bounds, Color backGroundColor,
            float transparencyBackGroundValue) {
        // Converts the Graphics object to a Graphics2D object, allowing access to
        // additional functionality and advanced drawing methods
        Graphics2D graphics2D = (Graphics2D) graphics;
        // This improves the quality of lines and edges by reducing staggering effects
        // (Set the antialiasing rendering
        // option to VALUE_ANTIALIAS_ON)
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Sets the transparency level of the background using an AlphaComposite object.
        // (AlphaComposite.SRC_OVER
        // indicates that transparency will be applied in the color scheme. The alpha
        // parameter specifies the transparency level)
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencyBackGroundValue));
        graphics2D.setColor(backGroundColor); // Sets the background fill color to the color specified by bgColor
        graphics2D.fill(bounds); // Fills the rectangle defined by the bounds with the specified background color
                                 // and transparency
    }

    /**
     * Draws text in a graphical context provided by graphics
     * 
     * @param graphics  Graphics object that represents the graphic context in which
     *                  the text will be drawn
     * @param panel     JPanel object that represents the panel on which the text
     *                  will be drawn
     * @param textColor Color object that represents the color of the text
     * @param text      string representing the text being drawn
     * @param xPosition Horizontal coordinate where the text will begin
     * @param yPosition Vertical coordinate where the text will begin
     */
    public void drawText(Graphics graphics, JPanel panel, Color textColor, String text, int xPosition, int yPosition) {
        graphics.setColor(textColor);
        graphics.setFont(new Font("MV Boli", Font.PLAIN, 50));
        // Text font metrics are obtained, which include information about the size and
        // position of characters in the font
        FontMetrics metrics2 = panel.getFontMetrics(graphics.getFont());
        // Draws the text string in the graphical context. The position at which the
        // text is drawn is
        // determined by the x,y coordinates. To center the text horizontally, subtract
        // half the width of the
        // text from the x coordinate
        graphics.drawString(text, xPosition - metrics2.stringWidth(text) / 2, yPosition);
    }
}
