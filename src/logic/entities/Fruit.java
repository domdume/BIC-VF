package logic.entities;

public class Fruit extends Entity {
    /**
     * Constructs a Fruit object with the given parameters.
     *
     * @param id the unique identifier of the fruit
     * @param x  the x-coordinate position of the fruit
     * @param y  the y-coordinate position of the fruit
     */
    public Fruit(int id, int x, int y) {
        super("Fruit", id, x, y);
    }

    /**
     * Gets the level identifier of the fruit.
     *
     * @return the level identifier, 2 if id is 0, otherwise 3
     */
    @Override
    public int getLevelId() {
        return this.iD == 0 ? 2 : 3;
    }
}