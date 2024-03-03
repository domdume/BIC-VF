package logic.entities;

import common.Constants;

public abstract class BaseEntity implements IEntity {
    private int x;
    private int y;
    protected int id;
    protected String name;
    protected int unit_size;
    protected String imagePath;
    protected int screen_width;
    protected int screen_height;

    public BaseEntity(String name, int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
        this.unit_size = Constants.UNIT_SIZE;
        this.screen_width = Constants.SCREEN_WIDTH;
        this.screen_height = Constants.SCREEN_HEIGHT;
        this.imagePath = parseImagePath(name, id);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getLevelId() {
        return this.id;
    }

    /**
     * it sets out the number designated for the class
     * 
     * @param id the number designated for the class in te matrix
     */
    public void setId(int id) {
        this.id = id;
        this.imagePath = parseImagePath(this.name, id);
    }

    @Override
    public String toString() {
        return String.format("Entity: %s - ( x: %d, y: %d )", this.name, this.x, this.y);
    }
}