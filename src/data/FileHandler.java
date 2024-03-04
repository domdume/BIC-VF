package data;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import common.Constant;
import common.FileMode;
import logic.entities.Entity;
import logic.levels.Level;
import logic.levels.Level1;

public class FileHandler {

    private FileMode mode;

    public FileHandler(FileMode mode) {
        this.mode = mode;
    }

    public void saveGame(ArrayList<Entity> entities, int currentScore, int levelIndex) {
        switch (this.mode) {
            case TEXT:
                this.saveEntityFile(entities, currentScore, levelIndex);
                break;

            case SERIALIZABLE:
                this.saveSerializedLevel(entities, currentScore, levelIndex);
                break;
        }
    }

    public Level loadGame() {
        switch (this.mode) {
            case TEXT:
                return this.loadEntityFile();

            case SERIALIZABLE:
                return this.loadSerializedLevel();

            default:
                return new Level1();
        }
    }

    /**
     * it's responsible for storing an entity's information in a file.
     *
     * @param entities     is an ArrayList of class Entity.
     * @param currentScore an integer representing the current score of the player.
     * @param levelIndex   an integer representing the index of the current level.
     */

    private void saveEntityFile(ArrayList<Entity> entities, int currentScore, int levelIndex) {
        StringBuilder data = new StringBuilder();
        int rows = Constant.SCREEN_WIDTH / Constant.UNIT_SIZE;
        int cols = Constant.SCREEN_HEIGHT / Constant.UNIT_SIZE;
        Level level = this.entitiesToLevel(entities, rows, cols, currentScore, levelIndex);
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                data.append(String.format("%d%s", level.getMap()[k][j], k < cols - 1 ? ", " : ""));
            }
            data.append("\n");
        }
        data.append("----------------------------------------\n");
        data.append(level.getCurrentScore());
        data.append("\n----------------------------------------\n");
        data.append(level.getLevelIndex());
        try (FileWriter file = new FileWriter("./game.txt")) {
            file.write(data.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            // Just Skip
        }
    }

    /**
     * it's responsible for storing an entity's information in a file.
     *
     * @param entities     is an ArrayList of class Entity.
     * @param currentScore an integer representing the current score of the player.
     * @param levelIndex   an integer representing the index of the current level.
     */

    private void saveSerializedLevel(ArrayList<Entity> entities, int currentScore, int levelIndex) {
        int rows = Constant.SCREEN_WIDTH / Constant.UNIT_SIZE;
        int cols = Constant.SCREEN_HEIGHT / Constant.UNIT_SIZE;
        Level level = this.entitiesToLevel(entities, rows, cols, currentScore, levelIndex);

        try (FileOutputStream file = new FileOutputStream("./game.ser")) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(level);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Just Skip
        }
    }

    /**
     * reads the game level serialized file and parses it in order to load the
     * position of game entities and score achieved at game saving time.
     *
     * @return new Level, if the file is invalid, it returns the Level 1 by default
     */
    private Level loadSerializedLevel() {

        Level level = null;
        try {
            FileInputStream fileIn = new FileInputStream("game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            level = (Level) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            // Just Skip
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            // Just Skip
        }
        return level == null ? new Level1() : level;
    }

    /**
     * reads the game entity file and parses it in order to load the position of
     * game entities
     * and score achieved at game saving time.
     *
     * @return new Level, if the file is invalid, it returns the Level 1 by default
     */
    private Level loadEntityFile() {
        String fullData = "";
        try (FileReader reader = new FileReader("game.txt")) {
            Scanner myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                fullData += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // If there is no file just skip
        } catch (IOException e) {
            // If there is any error just skip
        }

        return fullData == "" ? new Level1() : this.textToLevel(fullData);

    }

    /**
     * It uses the coordinates of the entity to calculate the position
     * in the matrix and assigns the entity level identifier to that position in the
     * matrix.
     *
     * @param entity of type Entity. The Entity object for which we want to get
     *               values.
     * @param map    an integer type of map. Stores the id of the Entity in the
     *               corresponding X and Y coordinates.
     * @return the updated matrix.
     */
    private int[][] getEntityValues(Entity entity, int[][] map) {
        int x = entity.getPositionX() / Constant.UNIT_SIZE;
        int y = entity.getPositionY() / Constant.UNIT_SIZE;
        int id = entity.getLevelId();
        map[y][x] = id;
        return map;
    }

    /**
     * Converts a list of entities into a game level.
     *
     * @param entities     is a List of entities to include in the level.
     * @param rows         an integer representing the rows in the level matrix.
     * @param cols         an integer representing the columns in the level matrix.
     * @param currentScore an integer representing the current score of the player.
     * @param levelIndex   an integer representing the index of the current level.
     * @return a Level object representing the generated level.
     */
    private Level entitiesToLevel(ArrayList<Entity> entities, int rows, int cols, int currentScore, int levelIndex) {
        int[][] map = new int[rows][cols];
        for (Entity entity : entities) {
            map = entity != null ? this.getEntityValues(entity, map) : map;
        }
        Level level = new Level();
        level.init(map, currentScore, levelIndex);
        return level;
    }

    /**
     * Turns a text into a Level object in the game.
     *
     * @param txt represents the level in a specific text format.
     * @return a Level representing the level generated from the text.
     */
    private Level textToLevel(String txt) {
        // Split the text into parts using the "-.*-" delimiter.
        String[] result = txt.trim().split("-.*-");

        // The first part of the text is converted to a two-dimensional array of
        // integers.
        int[][] map = this.txtToMatrix(result[0].trim());

        // The second part of the text is converted to the player's current score.
        int currentScore = Integer.parseInt(result[1].trim());

        // The third part of the text becomes the level index.
        int levelIndex = Integer.parseInt(result[2].trim());

        // Creates a new Level object and initializes it with the array, the current
        // score and the level index.
        Level level = new Level();
        level.init(map, currentScore, levelIndex);

        // Returns the created Level object.
        return level;
    }

    /**
     * Converts a text string to a two-dimensional array of integers.
     *
     * @param input is a text string representing the array in a specific format.
     * @return an array of integers generated from the text string.
     */
    private int[][] txtToMatrix(String input) {
        // Split string into rows using line break as delimiter.
        String[] rows = input.split("\n");

        // Gets the number of columns in the array using the length of the first row.
        int numCols = rows[0].split(", ").length;

        // Creates a two-dimensional integer matrix with the appropriate number of rows
        // and columns.
        int[][] matrix = new int[rows.length][numCols];

        // Iterate over each row of the text string.
        for (int i = 0; i < rows.length; i++) {
            // Divide the row into elements using comma and space as delimiters.
            String[] elements = rows[i].split(", ");

            // Iterates over each element and converts it to integer, assigning it to the
            // corresponding position in the array.
            for (int j = 0; j < numCols; j++) {
                matrix[j][i] = Integer.parseInt(elements[j]);
            }
        }

        // Return the generated matrix.
        return matrix;
    }

}