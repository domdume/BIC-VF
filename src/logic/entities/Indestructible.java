package logic.entities;

public class Indestructible extends Entity {

    public Indestructible(int id, int x, int y) {
        super("Indestructible", id, x, y);
    }

    @Override
    public int getLevelId() {
        return 8;
    }

}
