package logic.entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import logic.results.GameResult;
import logic.results.None;

public class Entity extends BaseEntity {

    public Entity(String name, int id, int x, int y) {
        super(name, id, x, y);
    }

    /**
     * it allows that the class who extends from entity checks the position and
     * proof that they are inside the limits
     * 
     * @param x position on x-axis
     * @param y position on y-axis
     * @return a true or false value depending on the position of the class
     */
    protected boolean withinBounds(int x, int y) {
        return !(x < 0 || x > this.screen_width - this.unit_size || y < 0 || y > this.screen_height - this.unit_size);
    }

    /**
     * the function goes through the list of entities and if the x and y coordinates
     * are equal to the coordinate it returns false and cannot move, otherwise if it
     * is not in the same coordinates it returns the function withinBounds (x,y),
     * which
     * indicates that it can move.
     * 
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities a grouping or an entities array
     * @return a false or true variable depending on whether the entity is in the
     *         given coordinates.
     */
    protected boolean canMove(int x, int y, ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            return !(entity != null && entity.getPositionX() == x && entity.getPositionY() == y);
        }
        return this.withinBounds(x, y);
    }

    /**
     * the function goes through the list of entities and if the x and y coordinates
     * are equal to the coordinate it returns false and cannot move, otherwise if it
     * is not in the same coordinates it returns the function withinBounds (x,y),
     * which
     * indicates that it can move.
     * 
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities a grouping or an entities array
     * @return
     */
    protected boolean canMoveIndestructible(int x, int y, ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            return !(entity != null && entity.getPositionX() == x && entity.getPositionY() == y);
        }
        return this.withinBounds(x, y);
    }

    /**
     * The method checks whether the entity has collided or not.
     * 
     * @param entity a parameter entity
     * @return a class calls none that indicates if there is a collision or no.
     */
    public GameResult handleCoalitions(Entity entity) {
        return new None();
    }

    /**
     * Moves the entity based on the game logic.
     * 
     * @param entities a list of entities
     * @return an integer indicating the movement status
     */
    public int move(ArrayList<Entity> entities) {
        return 0;
    }

    /**
     * Handles the key event for entity movement.
     * 
     * @param e        the KeyEvent representing the key pressed
     * @param entities a list of entities
     * @return an integer indicating the movement status
     */
    public int handleKeyEvent(KeyEvent e, ArrayList<Entity> entities) {
        return 0;
    }
}
