package logic.entities;

import java.util.ArrayList;

import common.Direction;

/**
 * Represents the enemy in the game, with methods to handle its movement and
 * collisions with other in-game objects (ice blocks and indestructibles)
 */
public class Enemy extends Entity {
    private int forward;
    private Direction direction;

    /**
     * int forward: An integer representing the direction of movement.
     * Direction: The direction in which the enemy is currently moving.
     */
    public Enemy(int id, int x, int y) {
        super("Enemy", id, x, y);
        this.direction = id == 0 ? Direction.VERTICAL : Direction.HORIZONTAL;
        this.forward = 1;
    }



    /**
     * Gets the level identifier of the enemy.
     *
     * @return the level identifier, 4 if id is 0, otherwise 5
     */
    @Override
    public int getLevelId() {
        return this.iD == 0 ? 4 : 5;
    }

    /**
     * the method checks if the object is on an ice surface at the given coordinates
     * and changes the direction of the object if it is the case.
     *
     * @param x      position on x-axis
     * @param y      position on y-axis
     * @param entity a parameter entity
     * @return a true or false value depending on whether the ice is on the given
     * coordinates or not.
     */
    private boolean checkIceBlock(int x, int y, Entity entity) {
        boolean iceFound = entity instanceof IceBlock && isSamePosition(x, y, entity);
        this.forward *= iceFound ? -1 : 1;
        return iceFound;
    }

    /**
     * the method checks if the object is on an indestructible object surface at the
     * given coordinates and changes the direction of the object if it is the case.
     *
     * @param x      position on x-axis
     * @param y      position on y-axis
     * @param entity a parameter entity
     * @return a true or false value depending on whether the obstacle is on the
     * given coordinates or not.
     */
    private boolean checkIndestructibleBlock(int x, int y, Entity entity) {
        boolean indestructibleFound = entity instanceof IndestructibleBlock && isSamePosition(x, y, entity);
        this.forward *= indestructibleFound ? -1 : 1;
        return indestructibleFound;
    }

    /**
     * The method checks if the object can move to the given coordinates without
     * colliding
     * with any ice entity in the array entities and without going outside the
     * boundaries of the screen.
     *
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities a grouping or an entities array
     * @return a true or false value depending on whether the object is out of
     * bounds.
     */
    @Override
    protected boolean canMove(int x, int y, ArrayList<Entity> entities) {
        boolean ice = false;
        for (int i = 0; i < entities.size() && !ice; i++) {
            ice = this.checkIceBlock(x, y, entities.get(i));
        }
        return this.withinBounds(x, y) && !ice;
    }

    /**
     * The method checks if the object can move to the given coordinates without
     * colliding
     * with any indestructible obstacle entity in the array entities and without
     * going outside the boundaries of the screen.
     *
     * @param x        position on x-axis
     * @param y        position on y-axis
     * @param entities a grouping or an entities array
     * @return a true or false value depending on whether the object is out of
     * bounds.
     */
    @Override
    protected boolean canMoveIndestructible(int x, int y, ArrayList<Entity> entities) {
        boolean indestructible = false;
        for (int i = 0; i < entities.size() && !indestructible; i++) {
            indestructible = this.checkIndestructibleBlock(x, y, entities.get(i));
        }
        return this.withinBounds(x, y) && !indestructible;
    }

    /**
     * The methods checks if the given coordinates are within the limits of the
     * screen,
     * and updates the forward value depending on the object's position.
     *
     * @param x position on x-axis
     * @param y position on y-axis
     * @return a true or false value if the given coordinates are within the screen
     * boundaries.
     */
    @Override
    protected boolean withinBounds(int x, int y) {
        boolean isWithinEdgeBounds = x < this.groundUsed || y < this.groundUsed;
        boolean isOutsideEdgeBounds = x > this.mapLimitWidth - this.groundUsed * 2
                || y > this.mapLimitHeight - this.groundUsed * 2;
        this.forward = isWithinEdgeBounds ? -1 : isOutsideEdgeBounds ? 1 : this.forward;
        return !(isWithinEdgeBounds || isOutsideEdgeBounds);
    }

    /**
     * updates the coordinates of the object in the specified direction, provided
     * there are no obstacles
     * 0r indestructible entities at the new position.
     *
     * @param entities a arrayList of entities
     * @return return 0 and updates the coordinates of the object
     */
    @Override
    public int move(ArrayList<Entity> entities) {
        int x = this.getPositionX();
        int y = this.getPositionY();
        y = this.direction == Direction.VERTICAL ? (canMove(x, y - this.groundUsed * 2 * this.forward, entities)
                && canMoveIndestructible(x, y - this.groundUsed * 2 * this.forward, entities))
                ? y - this.groundUsed * this.forward
                : y + this.groundUsed * this.forward
                : y;
        x = this.direction == Direction.HORIZONTAL ? (canMove(x - this.groundUsed * 2 * this.forward, y, entities)
                && canMoveIndestructible(x - this.groundUsed * 2 * this.forward, y, entities))
                ? x - this.groundUsed * this.forward
                : x + this.groundUsed * this.forward
                : x;
        this.setPositionX(x);
        this.setPositionY(y);
        return 0;
    }
}