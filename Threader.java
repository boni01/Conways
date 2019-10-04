/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: Threader
Project: Conways - Game of Life
Version: 0.0.1.0
 */
package sample;

import javafx.application.Platform;

import java.util.ArrayList;

public class Threader implements Runnable {

    private Thread t;                   // this Thread
    private Game game;                  // game object
    private ArrayList<Cell> cells;      // cell Array
    private double delay;               // gameSpeed converted to delay

    /* Constructor */
    public Threader(int gameSpeed, ArrayList<Cell> cells, Game game) {
        // set properties
        this.cells = cells;
        this.game = game;
        delay = Math.abs(((double) gameSpeed / 100) * 1000 - 1000) + 10; // Convert gameSpeed to delay
    }
    /* run Thread */
    public void run() {
        if (game.run) {
            try {
                Thread.sleep((int) delay);              // delay next step
                for (int i = 0; i < cells.size(); i++) {    // cont neighbors on cells
                    cells.get(i).countNeighbors();
                }
                Platform.runLater(() -> game.nextGen());    // step into next Gen
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /* start new Thread */
    public void start() {
        t = new Thread(this);
        t.start();
    }
}
