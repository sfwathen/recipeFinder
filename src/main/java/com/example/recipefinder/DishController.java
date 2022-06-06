package com.example.recipefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            PreparedStatement prepSt = RecipeFinder.conn.prepareStatement("select * from dish where dishID = ?");
            prepSt.setInt(1, RecipeFinder.currentDishID);

            ResultSet dish = prepSt.executeQuery();

            String dishName = "Dish Name";
            String dishDesc = "Dish Description";

            while (dish.next()){
                dishName = dish.getString(1);
                dishDesc = dish.getString(2);
            }

            dishNameLabel.setText(dishName);
            dishDescLabel.setText(dishDesc);

            prepSt = RecipeFinder.conn.prepareStatement("select * from recipe where dID = ?");
            prepSt.setInt(1, RecipeFinder.currentDishID);

            ResultSet recipe = prepSt.executeQuery();

            ObservableList<String> recipeItems = FXCollections.observableArrayList();

            while(recipe.next()){
                recipeItems.add(recipe.getInt("amount") + " " + recipe.getString("unit")
                        + " of " + recipe.getString("ingredient"));
            }

            ingredientListView.setItems(recipeItems);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void initData(int id) {
        RecipeFinder.currentDishID = id;
    }

    public void goToPrevious() throws IOException {
        RecipeFinder.navigateToNewPage(RecipeFinder.previousView);
    }
}
