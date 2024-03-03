package logic.entities;

import java.util.ArrayList;
import java.awt.event.KeyEvent;

import common.Direction;
import logic.results.Death;
import logic.results.GameResult;
import logic.results.None;
import logic.results.Points;

public class IceCream extends Entity {

    /**
     * Constructs an Ice Cream object with the given parameters
     * 
     * @param id the unique identifier of the Ice Cream entity
     * @param x  the x-coordinate position of the Ice Cream entity
     * @param y  the y-coordinate position of the Ice Cream entity
     */
    public IceCream(int id, int x, int y) {
        super("IceCream", id, x, y);
    }

    /**
     * handles keyboard events and performs different actions in a game or
     * application
     * 
     * @param e        the key linked to the action
     * @param entities an array of entities (different entities)
     * @return an integer with the value 0, indicating that the keyboard event has
     *         been handled correctly.
     */
    @Override
    public int handleKeyEvent(KeyEvent e, ArrayList<Entity> entities) {
        this.move(e, entities);
        this.setSpell(e);
        this.castDestroySpell(e, entities);
        this.castCreateSpell(e, entities);
        return 0;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getLevelId() {
        return this.id == 0 ? 6 : 7;
    }

    /**
     * Checks if the entity can move to the specified position (x, y) without
     * colliding with an Ice entity.
     *
     * @param x        the position on the x-axis
     * @param y        the position on the y-axis
     * @param entities a list of entities to check for collisions
     * @return true if the entity can move to the position, false if it would
     *         collide with an Ice entity or go out of bounds
     */
    @Override
    protected boolean canMove(int x, int y, ArrayList<Entity> entities) {
        entities = entities == null ? new ArrayList<>() : entities;
        boolean foundIce = false; // Cambio de nombre de tmp a foundIce para mayor claridad
        for (Entity entity : entities) {
            if (entity instanceof Ice && entity.getX() == x && entity.getY() == y) {
                foundIce = true;
                break; // Ya que se encontró un Ice, no necesitamos seguir buscando
            }
        }
        return this.withinBounds(x, y) && !foundIce;
    }

    /**
     * Checks if the IceCream entity can move to the specified position (x, y)
     * without colliding with an Indestructible entity.
     *
     * @param x        the target position on the x-axis
     * @param y        the target position on the y-axis
     * @param entities a list of entities to check for collisions
     * @return true if the IceCream can move to the position without colliding with
     *         an Indestructible entity or going out of bounds, false otherwise
     */
    @Override
    protected boolean canMoveIndestructible(int x, int y, ArrayList<Entity> entities) {
        entities = entities == null ? new ArrayList<>() : entities;
        boolean foundIndestructible = false;
        for (Entity entity : entities) {
            if (entity instanceof Indestructible && entity.getX() == x && entity.getY() == y) {
                foundIndestructible = true;
                break; // Ya que se encontró un Indestructible, no necesitamos seguir buscando
            }
        }
        return this.withinBounds(x, y) && !foundIndestructible;
    }

    /**
     * Handles the collision between the IceCream entity and another entity.
     * Determines the outcome of the collision.
     *
     * @param entity the other entity involved in the collision
     * @return - Death if collides with an Enemy
     *         - Points if collides with a Fruit
     *         - None if no collision occurs or with another type of entity
     */
    @Override
    public GameResult handleCoalitions(Entity entity) {
        return entity != null && entity.getX() == this.getX() && entity.getY() == this.getY()
                ? entity instanceof Enemy ? new Death() : entity instanceof Fruit ? new Points() : new None()
                : new None();
    }

    /**
     * calculates and returns a new X coordinate for an entity that will move in a
     * given direction
     * 
     * @param direction address to which the entity is addressed
     * @param x         position on x-axis
     * @param y         position on y-axis
     * @param entities  an array of entities
     * @return If the new X-coordinate is within the limits and is not occupied, the
     *         function returns
     *         the new X-coordinate. Otherwise, it returns the current X coordinate.
     */
    private int getNewX(Direction direction, int x, int y, ArrayList<Entity> entities) {
        int stepSize = direction == Direction.LEFT ? -1 : 1;
        return (canMove(x + this.unit_size * stepSize, y, entities)
                && canMoveIndestructible(x + this.unit_size * stepSize, y, entities))
                && direction != Direction.NONE
                        ? x + this.unit_size * stepSize
                        : x;
    }

    /**
     * calculates and returns a new Y coordinate for an entity that will move in a
     * given direction
     * 
     * @param direction address to which the entity is addressed
     * @param x         position on x-axis
     * @param y         position on y-axis
     * @param entities  an array of entities
     * @return If the new Y-coordinate is within the limits and is not occupied, the
     *         function returns
     *         the new Y-coordinate. Otherwise, it returns the current Y coordinate.
     */

    private int getNewY(Direction direction, int x, int y, ArrayList<Entity> entities) {
        int stepSize = direction == Direction.UP ? -1 : 1;
        return (canMove(x, y + this.unit_size * stepSize, entities)
                && canMoveIndestructible(x, y + this.unit_size * stepSize, entities))
                && direction != Direction.NONE
                        ? y + this.unit_size * stepSize
                        : y;
    }

    /**
     * The method moves an entity in a given direction according to the key pressed,
     * taking into account the boundaries of the board and the entities on it
     * 
     * @param e        the key associated with the direction
     * @param entities the entity (ice cream)
     */
    private void move(KeyEvent e, ArrayList<Entity> entities) {
        int x = this.getX();
        int y = this.getY();
        int code = e.getKeyCode();
        y = getY(entities, code, x, y);
        x = getX(entities, code, x, y);
        this.setY(y);
        this.setX(x);
    }

    private int getX(ArrayList<Entity> entities, int code, int x, int y) {
        return code == KeyEvent.VK_LEFT ? getNewX(Direction.LEFT, x, y, entities)
                : code == KeyEvent.VK_RIGHT ? getNewX(Direction.RIGHT, x, y, entities) : x;
    }

    private int getY(ArrayList<Entity> entities, int code, int x, int y) {
        return code == KeyEvent.VK_UP ? getNewY(Direction.UP, x, y, entities)
                : code == KeyEvent.VK_DOWN ? getNewY(Direction.DOWN, x, y, entities) : y;
    }

    // ID: 0 -> Create, ID: 1 -> Destroy
    /**
     * sets the spell identifier of an entity to 1 if the key pressed is SPACE and
     * the
     * current spell identifier is 0
     * 
     * @param e the key associated to the spell
     */
    private void setSpell(KeyEvent e) {
        this.setId(e.getKeyCode() == KeyEvent.VK_SPACE ? this.id == 0 ? 1 : 0 : this.id);
    }

    /**
     * The method adds an ice entity at a given position on the board, as long as
     * the entity calling the method
     * does not have an active spell and the position is available.
     * 
     * @param index    an integer representing the index of the position in the list
     *                 of entities
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities the entity (ice cream)
     * @return If the entity does not have an active spell and the position in the
     *         given index of the
     *         board is empty, the function creates a new ice entity and sets it to
     *         the corresponding position
     *         in the entity list, and returns true. Otherwise, the function returns
     *         false.
     */
    private boolean addIce(int index, int x, int y, ArrayList<Entity> entities) {
        boolean addedIce = this.id == 0 && entities.get(index) == null;
        entities.set(index, addedIce ? new Ice(0, x, y) : entities.get(index));
        return addedIce;
    }

    /**
     * The method removes an ice entity at a given position on the board, as long as
     * the entity calling the method
     * does not have an active spell and the position is available.
     * 
     * @param index    an integer representing the index of the position in the list
     *                 of entities
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities the entity (ice cream)
     * @return returns a true or false value depending on variable k1, which is set
     *         to true if the entity has an active
     *         spell, the position at the given index of the board is not empty, and
     *         the position of the entity
     *         on the board is the same as the given position. Otherwise, the
     *         function returns false.
     */
    private boolean removeIce(int index, int x, int y, ArrayList<Entity> entities) {
        boolean iceRemoved = this.id == 1 && entities.get(index) != null
                && y == entities.get(index).getY() && x == entities.get(index).getX();
        entities.set(index, iceRemoved ? null : entities.get(index));
        return iceRemoved;
    }

    /**
     * an address based on the code of the pressed key. The function is used to
     * determine
     * the horizontal direction in which an entity will move.
     * 
     * @param e the key associated to the horizontal direction
     * @return returns an address based on the code of the pressed key.
     */
    private Direction getKeyXDirection(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_A ? Direction.LEFT
                : e.getKeyCode() == KeyEvent.VK_D ? Direction.RIGHT : Direction.NONE;
    }

    /**
     * an address based on the code of the pressed key. The function is used to
     * determine
     * the vertical direction in which an entity will move.
     * 
     * @param e the key associated to the vertical direction
     * @return returns an address based on the code of the pressed key.
     */
    private Direction getKeyYDirection(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_S ? Direction.DOWN
                : e.getKeyCode() == KeyEvent.VK_W ? Direction.UP : Direction.NONE;
    }

    /**
     * Handles the process of casting a spell by the IceCream entity.
     *
     * @param id the spell identifier (0 for create, 1 for destroy)
     * @param entities the list of entities where the spell will be cast
     * @param i the index of the entity in the list where the spell will be
     * cast
     * @param x the x-coordinate position where the spell will be cast
     * @param y the y-coordinate position where the spell will be cast
     * @return true if the spell was successfully cast, false otherwise
     */
    private boolean castSpell(int id, ArrayList<Entity> entities, int i, int x, int y) {
        return id == 0 ? this.addIce(i, x, y, entities) : this.removeIce(i, x, y, entities);
    }

    /**
     * Handles the process of spell casting, determining the new coordinates based
     * on the key event
     * 
     * @param entities List of entities where the spell wil be cast
     * @param i        Entities index list
     * @param e        KeyEvent
     * @return Depends on the successfully cast spell
     */
    private boolean handleSpellCasting(ArrayList<Entity> entities, int i, KeyEvent e) {
        int y = this.getNewY(getKeyYDirection(e), this.getX(), this.getY(), null);
        int x = this.getNewX(getKeyXDirection(e), this.getX(), this.getY(), null);
        return isValidMove(x, y) && this.castSpell(this.id, entities, i, x, y);
    }

    /**
     * Checks if the new position (x, y) is valid
     *
     * @param x the new x-coordinate position
     * @param y the new y-coordinate position
     * @return true if the new position is different from the current position,
     * false otherwise
     */
    private boolean isValidMove(int x, int y) {
        return x != this.getX() || y != this.getY();
    }

    /**
     * Spell to create an obstacle entity at the location determined by the
     * key event
     * 
     * @param e        The Key Event that triggered the spell casting
     * @param entities List of entities where the spell will be cast
     */
    private void castCreateSpell(KeyEvent e, ArrayList<Entity> entities) {
        boolean spellCasted = false;
        for (int i = 0; i < entities.size() && !spellCasted; i++) {
            spellCasted = entities.get(i) == null && this.handleSpellCasting(entities, i, e);
        }
    }

    /**
     * Spell to destroy an ice entity at the location determined by the key event
     * 
     * @param e        the key associated to the direction of X and Y axis.
     * @param entities an entities array (different entities)
     */
    private void castDestroySpell(KeyEvent e, ArrayList<Entity> entities) {
        boolean spellCasted = false;
        for (int i = 0; i < entities.size() && !spellCasted; i++) {
            spellCasted = isIceEntity(entities, i) && handleSpellCasting(entities, i, e);
        }
    }

    /**
     * Determines if the entity at the specified index in the entities list is an
     * Ice entity
     *
     * @param entities the list of entities
     * @param index    the index of the entity to check
     * @return true if the entity at the specified index is an Ice entity, false otherwise
     */
    private static boolean isIceEntity(ArrayList<Entity> entities, int index) {
        return entities.get(index) != null && entities.get(index) instanceof Ice;
    }
}
