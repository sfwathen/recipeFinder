package com.example.recipefinder;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.sql.Statement;

public class HBoxCell extends HBox {
    Button button = new Button();

    HBoxCell(String buttonText, int dishID, String currentPage) {
        super();
        button.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(button, Priority.ALWAYS);

        button.setText(buttonText);
        button.setOnAction(e -> {
            try {
                Statement drop_table = RecipeFinder.conn.createStatement();
                drop_table.execute("drop table if exists searchlist" + RecipeFinder.currentUser);
                RecipeFinder.previousView = currentPage;
                RecipeFinder.navigateToNewPage("dish-view.fxml", dishID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.getChildren().addAll(button);
    }
}
