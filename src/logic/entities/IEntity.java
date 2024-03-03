package logic.entities;

public interface IEntity {
    /**
     * sets out a number as position
     * 
     * @param x the position on x-axis
     */
    void setX(int x);

    /**
     * sets out a number as position
     * 
     * @param y the position on y-axis
     */
    void setY(int y);

    /**
     * it obtains the x position
     * 
     * @return the position on x-axis
     */
    int getX();

    /**
     * it obtains the y position
     * 
     * @return the position on y-axis
     */
    int getY();

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
