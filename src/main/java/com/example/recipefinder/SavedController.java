package com.example.recipefinder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SavedController implements Initializable {

    String[] randomData = {
            "spades",
            "hearts",
            "diamonds",
            "clubs"
    };

    @FXML
    private VBox vboxxSaved;


    @FXML
    protected void goToHome() throws IOException {
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }

    @FXML
    void info() throws SQLException {
        List<HBox> hboxList = new ArrayList<>();
        String selectSQL = String.format("SELECT * FROM dish d, saved s WHERE d.dishID=s.savedID and s.savedUser=%s", RecipeFinder.currentUser);
//        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
//        preparedStatement.setInt(1,1001);
//        ResultSet rs = peparedStatement.executeQuery();
        ResultSet rs = null;
        while (rs.next()) {
            HBox hboxx = new HBox();
            Integer id = rs.getInt("dishID");
            String dishName = rs.getString("dishName");
            String description = rs.getString("description");

            //label for the dish name
            Button l1 = new Button(dishName);
            l1.setMinHeight(30);
            l1.setMinWidth(120);
            l1.setAlignment(Pos.CENTER);
            l1.setStyle("-fx-border-color:#4a4947; -fx-background-color: #b5b5b5; -fx-text-fill: #e01600; -fx-font-weight: bold; -fx-font: 16 arial;");
            l1.setOnAction(e -> {
                try {
                    RecipeFinder.navigateToNewPage("recipe-view.fxml", id, dishName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            //label for the description of the dish
            Label l2 = new Label(description);
            l2.setMinHeight(30);
            l2.setMinWidth(380);
            l2.setStyle("-fx-border-color:#4a4947;  -fx-border-radius: 5;");
            l2.setPadding(new Insets(5));

            hboxx.getChildren().add(l1);
            hboxx.getChildren().add(l2);
            HBox.setMargin(l1, new Insets(0, 10, 0, 0));
            hboxList.add(hboxx);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            info();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
