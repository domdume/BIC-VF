package logic.entities;

import common.MapLimit;

/**
 * An abstract base class representing entities in the game.
 */
public abstract class BaseEntity implements Identifiable, Positionable{
    private int positionX; // The x-coordinate position of the entity
    private int positionY; // The y-coordinate position of the entity
    protected int iD; // The unique identifier of the entity
    protected String name; // The name of the entity
    protected int groundUsed;
    protected String imagePath; // The path to the image associated with the entity
    protected int mapLimitWidth; // The width of the screen
    protected int mapLimitHeight; // The height of the screen


    /**
     * Constructs a BaseEntity object with the given parameters.
     * 
     * @param name the name of the entity
     * @param iD   the unique identifier of the entity
     * @param positionX    the x-coordinate position of the entity
     * @param positionY    the y-coordinate position of the entity
     */
    public BaseEntity(String name, int iD, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.iD = iD;
        this.name = name;
        this.groundUsed = 30;
        this.mapLimitWidth = MapLimit.MAP_ROW;
        this.mapLimitHeight = MapLimit.MAP_COLL;
        this.imagePath = parseImagePath(name, iD);
    }

    /**
     *
     * @param name the name of the class
     * @param id   the number with which it was designated
     * @return the image path associate with a class as a String
     */
    protected String parseImagePath(String name, int id) {
        return "images/" + name.toLowerCase() + id + ".png";
    }
    
    /**
     * Sets the x-coordinate position of the entity.
     * 
     * @param positionX the new x-coordinate position
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Sets the y-coordinate position of the entity.
     * 
     * @param positionY the new y-coordinate position
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Gets the x-coordinate position of the entity.
     * 
     * @return the x-coordinate position
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     * Gets the y-coordinate position of the entity.
     * 
     * @return the y-coordinate position
     */
    public int getPositionY() {
        return this.positionY;
    }

    /**
     * Gets the image path associated with the entity.
     * 
     * @return the image path as a String
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Gets the level identifier of the entity.
     * 
     * @return the level identifier
     */
    public int getLevelId() {
        return this.iD;
    }

    /**
     * it sets out the number designated for the class
     * 
     * @param iD the number designated for the class in the matrix
     */
    public void setID(int iD) {
        this.iD = iD;
        this.imagePath = parseImagePath(this.name, iD);
    }
}
