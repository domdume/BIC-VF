package logic.levels;

public class Level2 extends Level {

    // 0: empty, 1: obstacle, 2: banana, 3: watermelon
    // 4: vertical enemy, 5: horizontal enemy, 6: player
    // 7: NonDestructible Obstacle
    private final int[][] map = {
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 8 },
            { 8, 0, 0, 5, 0, 1, 0, 1, 0, 0, 1, 0, 0, 8 },
            { 8, 1, 1, 1, 0, 5, 0, 1, 0, 1, 1, 0, 0, 8 },
            { 8, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 4, 0, 8 },
            { 8, 1, 1, 1, 1, 0, 1, 1, 0, 5, 0, 1, 1, 8 },
            { 8, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 8 },
            { 8, 1, 4, 0, 4, 0, 6, 1, 1, 1, 1, 0, 1, 8 },
            { 8, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 8 },
            { 8, 0, 1, 1, 0, 1, 3, 1, 0, 1, 1, 1, 1, 8 },
            { 8, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 8 },
            { 8, 0, 4, 1, 0, 5, 0, 1, 0, 4, 1, 1, 0, 8 },
            { 8, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
    };

    public Level2() {
        super.init(this.map, 0, 2);
    }

}
