package logic.entities;

public class Fruit extends Entity {
    public Fruit(int id, int x, int y) {
        super("Fruit", id, x, y);
    }

    @Override
    public int getLevelId() {
        return this.iD == 0 ? 2 : 3;
    }
}