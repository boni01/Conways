/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: Menu Class
Project: Conways - Game of Life
Version: 0.0.0.1;
 */
package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Menu {

    public Menu(Stage menuStage) {
        try {
            initMenu(menuStage);
        } catch (Exception e) {
            e.printStackTrace();;
            System.exit(-1);
        }
    }
    /* Create new Menu Window */
    private void initMenu(Stage menuStage) throws Exception {
        // Load fxml
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
        GridPane root = (GridPane) fxmlLoader.load();
        // create window
        menuStage.setScene(new Scene(root, 500, 350));
        menuStage.setResizable(false);
        menuStage.setTitle("Conways - Launcher");
        menuStage.centerOnScreen();
        menuStage.show();
    }
}
