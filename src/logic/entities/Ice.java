package logic.entities;

public class Ice extends Entity {
    public Ice(int id, int x, int y) {
        super("Ice", id, x, y);
    }

    @Override
    public int getLevelId() {
        return 1;
    }
}