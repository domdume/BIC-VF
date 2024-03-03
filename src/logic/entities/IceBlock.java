package logic.entities;

public class IceBlock extends Entity {
    public IceBlock(int id, int x, int y) {
        super("IceBlock", id, x, y);
    }

    @Override
    public int getLevelId() {
        return 1;
    }
}