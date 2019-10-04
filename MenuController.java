/*
Autor:   Nathan Péray
Datum:   16.04.2019
Content: MenuController
Project: Conways - Game of Life
Version: 0.0.0.1
 */
package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private GridPane root;
    @FXML
    private ChoiceBox gridSize;
    @FXML
    private Slider speed;
    @FXML
    private Label speedInfo;
    @FXML
    private Slider spawn;
    @FXML
    private Label spawnInfo;
    @FXML
    private Spinner<Integer> min;
    @FXML
    private Spinner<Integer> max;
    @FXML
    private Spinner<Integer> mew;
    @FXML
    private Button start;

    /* initialize MenuController, Auto called by JavaFX */
    @FXML
    private void initialize() {
        // Set DefaultValues
        setGridOptions();
        setSpinner();
        speed.setValue(50);
        spawn.setValue(25);
        slide(speed);
        slide(spawn);
        // Set Action Listeners;
        start.setOnAction(e -> startGame());
        min.setOnMouseClicked(e -> verifyVal(min));
        max.setOnMouseClicked(e -> verifyVal(max));
        spawn.setOnMouseDragged(e -> slide(spawn));
        spawn.setOnMouseReleased(e -> slide(spawn));
        speed.setOnMouseDragged(e -> slide(speed));
        speed.setOnMouseReleased(e -> slide(speed));
    }
    /* add min, max Values to Sliders */
    @FXML
    private void setSpinner() {
        min.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 1));
        max.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 4));
        mew.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 3));
    }
    /* start game */
    @FXML
    private void startGame() {
        int[] confArr = getValues();
        if (confArr[0] != 0) { // if gridsize isset, start game
            hideWindow();
            Game game = new Game((Stage) root.getScene().getWindow(), confArr);
        } else {
            gridSize.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }
    /* ensure spinner values meet requirements */
    @FXML
    private void verifyVal(Spinner sender) {
        if (sender == min) {
            if (min.getValue() >= max.getValue()) {
                max.getValueFactory().setValue(min.getValue() + 1);
            }
        } else if (sender == max) {
            if (max.getValue() <= min.getValue()) {
                min.getValueFactory().setValue(max.getValue() - 1);
            }
        }  else {
            return;
        }
    }
    /* Wirte Slider value into label */
    @FXML
    private void slide(Slider sender) {
        int value = (int) Math.round(sender.getValue());
        if (sender == speed) {
            speedInfo.setText("Spielgeschwindigkeit:    " + value + "%");
        } else if (sender == spawn) {
            spawnInfo.setText("Initial Spawn Chance:    " + value + "%");
        } else {
            return;
        }
    }
    /* add options to Size selector */
    private void setGridOptions() {
        String[] sizes = {"10x10", "15x15", "20x20", "25x25", "30x30", "40x40", "50x50", "100x100"};
        gridSize.setItems(FXCollections.observableArrayList(sizes));
    }
    /* get values form inputs and create Array */
    private int[] getValues() {
        int gridVal = getGridSize();
        int spawnVal = (int) Math.round(spawn.getValue());
        int speedVal = (int) Math.round(speed.getValue());
        int minVal = min.getValue();
        int maxVal = max.getValue();
        int mewVal = mew.getValue();
        int[] returnArr = {gridVal, spawnVal, speedVal, minVal, maxVal, mewVal};
        return returnArr;

    }
    /* get int from gridsize */
    private int getGridSize() {
        String val = (String) gridSize.getValue();
        if (val != null) {
            String[] splitter = val.split("x");
            return Integer.parseInt(splitter[0]);
        } else {
            return 0;
        }

    }
    /* hide window on start */
    private void hideWindow() {
        Stage menu = (Stage) root.getScene().getWindow();
        menu.close();
    }
}
