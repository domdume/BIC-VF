package logic.entities;

public class IndestructibleBlock extends Entity {

    public IndestructibleBlock(int id, int x, int y) {
        super("IndestructibleBlock", id, x, y);
    }

    @Override
    public int getLevelId() {
        return 8;
    }

}
