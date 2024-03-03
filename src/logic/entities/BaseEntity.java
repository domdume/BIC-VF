package logic.entities;

import common.Constant;

public abstract class BaseEntity implements Positionable, Identifiable {
    private int positionX;
    private int positionY;
    protected int iD;
    protected String name;
    protected int unit_size;
    protected String imagePath;
    protected int screen_width;
    protected int screen_height;

    public BaseEntity(String name, int iD, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.iD = iD;
        this.name = name;
        this.unit_size = Constant.UNIT_SIZE;
        this.screen_width = Constant.SCREEN_WIDTH;
        this.screen_height = Constant.SCREEN_HEIGHT;
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getLevelId() {
        return this.iD;
    }

    /**
     * It sets out the number designated for the class
     * @param iD the number designated for the class in te matrix
     */
    public void setID(int iD) {
        this.iD = iD;
        this.imagePath = parseImagePath(this.name, iD);
    }

//    /**
//     * Readable representation of an object. Builds the output string with a specific format.
//     * @return a string that follows the format of the entity name and its X and Y coordinates
//     */
//    @Override
//    public String toString() {
//        return String.format("Entity: %s - ( x: %d, y: %d )", this.name, this.positionX, this.positionY);
//    }
}