package CalculatorProject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class historyController {
    @FXML
    private ListView<String> historyList;

    public void initializeCalculations(ArrayList<String> calcuHistory){
        calcuHistory.forEach((calculation) -> {
            historyList.getItems().add(calculation);
        } );
    }
}
