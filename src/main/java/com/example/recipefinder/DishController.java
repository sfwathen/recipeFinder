package com.example.recipefinder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DishController implements Initializable{
    @FXML
    private Label dishNameLabel;
    @FXML
    private Label dishDescLabel;
    @FXML
    private ListView ingredientListView;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}