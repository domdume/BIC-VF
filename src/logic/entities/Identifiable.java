package logic.entities;

public interface Identifiable {
    /**
     * it obtains the number designated for the class
     *
     * @return the number designated for the class in the matrix map
     */
    int getLevelId();
    /**
     *
     * @return the image path associated to the class
     */
    String getImagePath();
    /**
     *
     * @return a string representation of the entity
     */
    @Override
    String toString();
}