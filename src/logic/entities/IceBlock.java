package logic.entities;

public class IceBlock extends Entity {
    /**
     * Constructs an Ice object with the given parameters.
     *
     * @param id the unique identifier of the ice entity
     * @param x  the x-coordinate position of the ice entity
     * @param y  the y-coordinate position of the ice entity
     */
    public IceBlock(int id, int x, int y) {
        super("Ice", id, x, y);
    }
    /**
     * Gets the level identifier associated with ice entities.
     *
     * @return the level identifier for ice entities
     */
    @Override
    public int getLevelId() {
        return 1;
    }
}