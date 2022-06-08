package com.example.recipefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class addrecipeController {
    @FXML
    private TextField dishname;

    @FXML
    private ListView listing;

    @FXML
    private Button adding;

    @FXML
    private VBox ingbox;

    @FXML
    private VBox ingbox1;
    @FXML
    private Button submit;

    @FXML
    private TextField description;

    ObservableList<HBox> results = FXCollections.observableArrayList();
    String name;

    String desc;

    String dishInsert;
    String ingredientsInsert[] = new String[50];
    int size = 0;

    TextField ingredientNameBox[] = new TextField[50];
    TextField ingredientBox[] = new TextField[50];
    String ingredients[] = new String[50];
    String ingredientsName[] = new String[50];

    public void submitEntry(ActionEvent event)
    {
        name = dishname.getText();
        desc = description.getText();

        int dishExists = queryDishes(name, desc);
        if (dishExists != -1) {
            //alert user that dish with that name and desc already exists
            return;
        }

        try {
            dishInsert = "INSERT INTO dish (dishName, description) VALUES (?, ?)";
            PreparedStatement preparedInsert = RecipeFinder.conn.prepareStatement(dishInsert);
            preparedInsert.setString(1, name);
            preparedInsert.setString(2, desc);
            preparedInsert.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            // error inserting dish
            return;
        }

        int newDishID = queryDishes(name, desc);

        for(int x = 0; x < size; x++)
        {
            System.out.println(ingredientBox[x].getText() + " " + ingredientNameBox[x].getText());
            ingredients[x] = ingredientBox[x].getText();
            ingredientsName[x] = ingredientNameBox[x].getText();
        }

        // add recipes
        for (int i = 0; i < size; i++) {
            String amountStr = ingredients[i].substring(0,1);
            int amount = Integer.parseInt(amountStr);
            String unit = ingredients[i].substring(2);
            String ingredient = ingredientsName[i];
            insertRecipe(amount, ingredient, newDishID, unit);
        }

    }

    /**
     * Returns -1 if no dish found, otherwise returns dishID
     * @param name
     * @param description
     * @return
     */
    private int queryDishes(String name, String description) {
        try {
            String selectSQL = "SELECT dishID FROM dish WHERE dishName=? AND description=?";
            PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            ResultSet rs = preparedStatement.executeQuery();

            try {
                rs.next();
                int dishID = rs.getInt(1);
                return dishID;
            }
            catch (Exception e) {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void insertRecipe(int amount, String ingredient, int dishID, String unit) {
        try {
            String insertSQL = "INSERT INTO recipe (amount, ingredient, dID, unit) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(insertSQL);
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, ingredient);
            preparedStatement.setInt(3, dishID);
            preparedStatement.setString(4, unit);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void AddTextField(ActionEvent event)  {
        TextField newField = new TextField();
        TextField newField1 = new TextField();
        HBox h = new HBox();
        h.getChildren().add(newField);
        h.getChildren().add(newField1);
        ingredientBox[size] = newField;
        ingredientNameBox[size] = newField1;
        size +=1;
        results.add(h);
        listing.setItems(results);

    }

    @FXML
    private void goBackToMenu() throws IOException {
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }

}