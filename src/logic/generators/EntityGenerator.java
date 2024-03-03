package logic.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Constant;
import logic.entities.*;
import logic.levels.Level;

public final class EntityGenerator {

    /**
     * Generates a list of entities based on the provided level configuration.
     * 
     * @param level the level for which entities need to be generated
     * @return a list of entities generated for the given level
     */
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

        /**
         * Constructs a Values object with the specified parameters.
         * 
         * @param row the row index of the entity
         * @param col the column index of the entity
         * @param val the value representing the type of entity
         */
        public Values(int row, int col, int val) {
            this.params = new Params(row, col, val);
        }

        /**
         * Calculates and returns the x-coordinate position of the entity.
         * 
         * @return the x-coordinate position of the entity
         */
        private int getX() {
            return this.params.row * Constant.UNIT_SIZE;
        }

        /**
         * Calculates and returns the y-coordinate position of the entity.
         * 
         * @return the y-coordinate position of the entity
         */
        private int getY() {
            return this.params.col * Constant.UNIT_SIZE;
        }

        /**
         * Determines the ID of the entity based on its value.
         * 
         * @return the ID of the entity
         */
        private int getId() {
            int val = this.params.val;
            return val == 1 || val == 2 || val == 4 || val == 6 || val == 8 ? 0 : 1;
        }

        /**
         * Creates a dictionary mapping entity values to entity objects.
         * 
         * @return a map containing entity objects for different entity values
         */
        private Map<Integer, Entity> storage() {
            Map<Integer, Entity> entityDictionary = new HashMap<>();
            entityDictionary.put(8, new IndestructibleBlock(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(1, new IceBlock(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(2, new Fruit(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(3, new Fruit(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(4, new Enemy(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(5, new Enemy(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(6, new IceCream(this.getId(), this.getX(), this.getY()));
            entityDictionary.put(7, new IceCream(this.getId(), this.getX(), this.getY()));

            return entityDictionary;
        }

        /**
         * Retrieves the entity object based on the stored parameters.
         * 
         * @return the entity object corresponding to the stored parameters
         */
        public Entity getEntity() {
            return this.storage().getOrDefault(this.params.val, null);
        }
    }

    /**
     * A record class to represent the parameters used for generating entities.
     */
    private static record Params(int row, int col, int val) {
    }

}
