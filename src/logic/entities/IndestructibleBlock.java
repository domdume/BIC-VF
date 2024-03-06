package logic.entities;

public class IndestructibleBlock extends Entity {
    /**
     * Constructs an Indestructible object with the given parameters.
     *
     * @param id the unique identifier of the entity
     * @param x  the x-coordinate position of the entity
     * @param y  the y-coordinate position of the entity
     */
    public IndestructibleBlock(int id, int x, int y) {
        super("Indestructible", id, x, y);
    }

    /**
     * Retrieves the level ID associated with the Indestructible entity.
     *
     * @return the level ID (always 8 for Indestructible entities)
     */
    @Override
    public int getLevelId() {
        return 8;
    }
}