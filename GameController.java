/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: GameController
Project: Conways - Game of Life
Version: 0.0.0.1
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GameController {

    private int gridSize;           // amount of rows and Columns in Grid
    private int speed;              // Speed of game
    @FXML
    private GridPane gameGrid;      // GridContainer for Game
    @FXML
    public Slider gameSpeed;        // slider to change Speed while running
    @FXML
    public Label generationLabel;

    /* constructor */
    public GameController(int gridSize, int speed) {
        // Set properties
        this.gridSize = gridSize;
        this.speed = speed;
    }
    /* initialize GameController, auto Called by JavaFX*/
    @FXML
    private void initialize() {
        gameSpeed.setValue(speed);
        buildGrid();
    }
    /* create rows and Columns in grid */
    @FXML
    private void  buildGrid() {
        gameGrid.setStyle("-fx-background-color: #CCCCCC");
        gameSpeed.setValue(speed);
        for (int i = 0; i < gridSize ; i++) {
            RowConstraints r = new RowConstraints();
            r.setPercentHeight((double) 100 / gridSize);
            gameGrid.getRowConstraints().add(i, r);
            ColumnConstraints c = new ColumnConstraints();
            c.setPercentWidth((double) 100 / gridSize);
            gameGrid.getColumnConstraints().add(i, c);
        }
    }
    /* Add Cell to Grid */
    @FXML
    public void addCell(Pane cell, int x, int y) {
        gameGrid.add(cell, x, y);
    }
}
