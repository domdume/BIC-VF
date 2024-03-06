package logic.entities;

/**
 * It has a set of methods that allow you to obtain information about the level id, the
 * image path, and a string representation of an entity in a game or other context
 */
public interface Identifiable {
    /**
     * it obtains the number designated for the class
     *
     * @return the number designated for the class in the matrix map
     */
    int getLevelId();

    /**
     * @return the image path associated to the class
     */
    String getImagePath();

    /**
     * @return a string representation of the entity
     */
    @Override
    String toString();
}