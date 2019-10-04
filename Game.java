/*
Autor:    Nathan Péray
Datum:    16.04.2019
Content:  Game Class
Project: Conways - Game of Life
Version:  0.0.1.0
 */

package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {

    private Stage gameStage;            // Game Window
    private GameController controller;  // Controller for Game Window
    private GridPane root;              // Masterelement of GameWindow

    private int generation;
    private int gridSize;               // Amount of Rows / cols in grid
    private int spawn;                  // Initial Chance of living Cell
    private int speed;                  // Game Speed
    private int min;                    // least living neighbor cells to survive
    private int max;                    // most living neighbor cells to survive
    private int mew;                    // Amount of living neighbors cells to be born
    private Stage parent;               // menu window
    private Threader t;                 // Seperate Thread for Interval and Neigbor counting

    public ArrayList<Cell> cells;       // List of Cells
    public boolean run;                 // Boolean to interrupt gameLoop

    /* Constructor */
    public Game(Stage parent, int[] confArr) {
        // Set Properties
        this.parent = parent;
        generation = 0;
        gridSize = confArr[0];
        spawn = confArr[1];
        speed = confArr[2];
        min = confArr[3];
        max = confArr[4];
        mew = confArr[5];
        cells = new ArrayList<Cell>();
        // Start game or stop programm
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    /* Initialize Game */
    private void initGame() throws Exception {
        controller = new GameController(gridSize, speed);
        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("game.fxml"));
        // Set GameController
        fxmlLoader.setController(controller);
        root = fxmlLoader.load();
        // New Window for Game
        gameStage = new Stage();
        gameStage.setScene(new Scene(root, 900, 900));
        gameStage.setTitle("Conways - Generation: 0");
        gameStage.centerOnScreen();
        gameStage.show();
        // Set action Listeners
        gameStage.setOnCloseRequest(e -> endGame());
        controller.gameSpeed.setOnMouseReleased(e -> updateSpeed());
        // Create Cells
        createCells();
        // Start gameLoop
        run = true;
        t = new Threader(speed, cells, this);
        t.start();
    }
    /* Create Array of Cells */
    private void createCells() {
        for (int i = 0; i < gridSize; i++) { // Row Loop
            for (int j = 0; j < gridSize; j++) { // Column Loop
                // add Cell to Array
                int index = i * gridSize + j;
                cells.add(new Cell(this, index, gridSize, min, max, mew));
                controller.addCell(cells.get(index).getPane(), j, i);
            }
        }
        // Randomize Initial Spawn
        for (int i = 0; i < cells.size(); i++) {
            if (Math.floor(Math.random() * 100) + 1 <= spawn) {
                cells.get(i).live();
            }
        }
    }
    /* Continue gameLoop */
    public void nextGen() {
        generation++;
        controller.generationLabel.setText("Conways - Generation: " + generation);
        gameStage.setTitle("Conways - Generation: " + generation);
        for (int i = 0; i < cells.size(); i++) { // Loop through cells and go to next Gen
            cells.get(i).nextGen();
        }
        if(run) {
            // Continue gameLoop
            t = new Threader(speed, cells, this);
            t.start();
        }
    }
    /* End Game */
    private void endGame() {
        // interrup loop
        run = false;
        // Show menu Window
        parent.show();
    }
    /* Change gameSpeed */
    public void updateSpeed() {
        // Set new Value
        double newSpeed = Math.round(controller.gameSpeed.getValue());
        speed = (int) newSpeed;
    }
}
