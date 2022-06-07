package com.example.recipefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            PreparedStatement prepSt = RecipeFinder.conn.prepareStatement("select * from dish where dishID = ?");
            prepSt.setInt(1, RecipeFinder.currentDishID);

            ResultSet dish = prepSt.executeQuery();

            String dishName = "Dish Name";
            String dishDesc = "Dish Description";

            while (dish.next()){
                dishName = dish.getString("dishName");
                dishDesc = dish.getString("description");
            }

            dishNameLabel.setText(dishName);
            dishDescLabel.setText(dishDesc);

            prepSt = RecipeFinder.conn.prepareStatement("select savedID from saved where savedUser = ?");
            prepSt.setString(1, RecipeFinder.currentUser);
            ResultSet saved = prepSt.executeQuery();
            while(saved.next()){
                if(saved.getInt("savedID") == RecipeFinder.currentDishID){
                    saveButton.setText("Saved!");
                }
            }

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

    public void goToPrevious() throws IOException {
        RecipeFinder.navigateToNewPage(RecipeFinder.previousView);
    }

    public void addToSaved(ActionEvent actionEvent) throws Exception{
        if(!saveButton.getText().equals("Saved!")) {
            saveButton.setText("Saved!");
            PreparedStatement prepSt = RecipeFinder.conn.prepareStatement("insert into saved(savedUser, savedID) values (?,?)");
            prepSt.setString(1, RecipeFinder.currentUser);
            prepSt.setInt(2, RecipeFinder.currentDishID);
            prepSt.executeUpdate();
        }
    }
}
