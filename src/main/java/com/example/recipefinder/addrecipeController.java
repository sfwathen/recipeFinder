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
    private Button submit;

    String name;
    int size = 0;

    TextField ingredientBox[] = new TextField[25];
    String ingredients[] = new String[25];
    public void submitEntry(ActionEvent event)
    {
        name = dishname.getText();
        System.out.println(name);
        for(int x = 0; x < size; x++)
        {
            System.out.println(ingredientBox[x].getText());
            ingredients[x] = ingredientBox[x].getText();
        }
    }

    @FXML
    private void AddTextField(ActionEvent event)  {
        TextField newField = new TextField();
        ingredientBox[size] = newField;
        size +=1;
        ingbox.getChildren().add(newField);}

}