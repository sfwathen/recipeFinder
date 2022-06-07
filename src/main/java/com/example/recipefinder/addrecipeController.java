package com.example.recipefinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class addrecipeController {
    @FXML
    private TextField dishname;

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
        dishInsert = "INSERT INTO dish (dishName, description) VALUES (\'"+ name +"\',"+"\'"+desc+"\');";
        System.out.println(dishInsert);
        for(int x = 0; x < size; x++)
        {
            System.out.println(ingredientBox[x].getText() + " " + ingredientNameBox[x].getText());
            ingredients[x] = ingredientBox[x].getText();
            ingredientsName[x] = ingredientNameBox[x].getText();


        }
    }

    @FXML
    private void AddTextField(ActionEvent event)  {
        TextField newField = new TextField();
        TextField newField1 = new TextField();

        ingredientBox[size] = newField;
        ingredientNameBox[size] = newField1;
        size +=1;
        ingbox.getChildren().add(newField);
        ingbox1.getChildren().add(newField1);

    }

}