package logic.entities;

public interface Positionable {
    /**
     * sets out a number as position
     * 
     * @param positionX the position on x-axis
     */
    void setPositionX(int positionX);

    /**
     * sets out a number as position
     * 
     * @param positionY the position on y-axis
     */
    void setPositionY(int positionY);

    /**
     * it obtains the x position
     * 
     * @return the position on x-axis
     */
    int getPositionX();

    /**
     * it obtains the y position
     * 
     * @return the position on y-axis
     */
    int getPositionY();

}
