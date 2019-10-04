/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: Main - Startapp
Project: Conways - Game of Life
Version: 0.0.0.1
 */
package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Menu mainMenu = new Menu(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
