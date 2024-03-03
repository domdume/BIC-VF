package logic.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Constants;
import logic.entities.*;
import logic.levels.Level;

public final class EntityGenerator {

    public static ArrayList<Entity> generate(Level level) {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (int j = 0; j < level.getMap().length; j++) {
            for (int i = 0; i < level.getMap()[j].length; i++) {
                entities.add(new Values(i, j, level.getMap()[j][i]).getEntity());
            }
        }
        return entities;
    }

    private static class Values {

        private Params params;

        public Values(int row, int col, int val) {
            this.params = new Params(row, col, val);
        }

        private int getX() {
            return this.params.row * Constants.UNIT_SIZE;
        }

        private int getY() {
            return this.params.col * Constants.UNIT_SIZE;
        }

        private int getId() {
            int val = this.params.val;
            return val == 1 || val == 2 || val == 4 || val == 6 || val == 8 ? 0 : 1;
        }

        private Map<Integer, Entity> storage() {
            Map<Integer, Entity> entityDictionary = new HashMap<>();
            entityDictionary.put(8, new Indestructible(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(1, new Ice(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(2, new Fruit(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(3, new Fruit(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(4, new Enemy(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(5, new Enemy(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(6, new IceCream(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(7, new IceCream(this.getId(), this.getX(), this.getY()));

            return entityDictionary;
        }

        public Entity getEntity() {
            return this.storage().getOrDefault(this.params.val, null);
        }
    }

    private static record Params(int row, int col, int val) {
    }

}
