package logic.levels;

public class Level1 extends Level {

    // 0: empty, 1: ice block, 2: banana, 3: watermelon
    // 4: vertical enemy, 5: horizontal enemy, 6: player, 8: indestructible block
    private final int[][] map = {
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {8, 5, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 8},
            {8, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 4, 8},
            {8, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 8},
            {8, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 2, 3, 8},
            {8, 0, 2, 0, 1, 0, 0, 0, 0, 1, 0, 1, 3, 8},
            {8, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 2, 3, 8},
            {8, 0, 2, 0, 1, 0, 6, 0, 0, 1, 0, 1, 3, 8},
            {8, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 2, 3, 8},
            {8, 0, 2, 0, 1, 1, 1, 1, 1, 1, 0, 1, 3, 8},
            {8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 8},
            {8, 0, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 8},
            {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 8},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
    };

    public Level1() {
        super.init(this.map, 0, 1);
    }

}
