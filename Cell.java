/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: Cell Class
Project: Conways - Game of Life
Version: 0.0.0.1
 */
package sample;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class Cell  {

    private Game parent;        // Game object
    private Pane cell;          // Cell element in Window
    private int index;          // Index in Array
    private int neighbors;      // amount of living neighbors
    private int gridSize;       // amount of rows and columns in gameGrid
    private int min;            // least living neighbors to survive
    private int max;            // most living neighbors to survive
    private int mew;            // amount of living neighbors to be born

    public boolean alive;       // living status of cell

    /* Constructor */
    public Cell(Game parent, int index, int gridSize, int min, int max, int mew) {
        // Set cell properties
        this.parent = parent;
        this.index = index;
        this.gridSize = gridSize;
        this.min = min;
        this.max = max;
        this.mew = mew;
        alive = false;
        this.index = index;
        // Initialize new Cell
        cell = new Pane();
        cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        cell.setVisible(true);
        die();
        neighbors = 0;
    }
    /* Return Pane Element */
    public Pane getPane() {
        return cell;
    }
    /* Kill cell */
    public void die() {
        cell.setStyle("-fx-background-color: #FFFFFF");
        alive = false;
    }
    /* revive Cell */
    public void live() {
        cell.setStyle("-fx-background-color: #000000");
        alive = true;
    }
    /* count living neighbors */
    public void countNeighbors() {
        neighbors = 0;
        // ROW ABOVE
        if (index >= gridSize) {
            if (index % gridSize != 0 && checkNeighbor(index - gridSize - 1)) {
                neighbors++;
            }
            if (index % gridSize != gridSize - 1 && checkNeighbor(index - gridSize + 1)) {
                neighbors++;
            }
            if (checkNeighbor(index - gridSize)) {
                neighbors++;
            }
        }
        // ROW BELOW
        if (index < parent.cells.size() - gridSize) {
            if (index % gridSize != 0 && checkNeighbor(index + gridSize - 1)) {
                neighbors++;
            }
            if (index % gridSize != gridSize - 1 && checkNeighbor(index + gridSize + 1)) {
                neighbors++;
            }
            if (checkNeighbor(index + gridSize)) {
                neighbors++;
            }
        }
        // IN LINE
        if (index > 0 && index % gridSize != 0 && checkNeighbor(index - 1)) {
           neighbors++;
        }
        if (index < parent.cells.size() && index % gridSize != gridSize - 1 && checkNeighbor(index + 1)) {
            neighbors++;
        }
    }
    /* check if neighbor is alive */
    private boolean checkNeighbor(int index) {
        return parent.cells.get(index).alive;
    }
    /* Step in to NextGen */
    public void nextGen() {
        if (neighbors <= min) { // die from low population
            die();
        } else if (neighbors >= max) { // die from to much population
            die();
        } else if (!alive && neighbors == mew) { // rebirth from perfect population
            live();
        }
    }
}
