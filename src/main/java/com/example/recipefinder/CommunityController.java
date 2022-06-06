package com.example.recipefinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommunityController implements Initializable {

    String[] randomData = {
            "spades",
            "hearts",
            "diamonds",
            "clubsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "why"
    };

    @FXML
    private VBox vboxx;

    @FXML
    protected void goToHome() throws IOException {
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }

    @FXML
    void info() throws SQLException {
        List<HBox> hboxList = new ArrayList<>();
        String selectSQL = "SELECT * FROM dish";
        PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();
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
                    RecipeFinder.previousView = "saved-view.fxml";
                    RecipeFinder.navigateToNewPage("dish-view.fxml", id);
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

//    @FXML
//    void info() {
//        List<HBox> hboxList = new ArrayList<>();
//        for (int i = 0; i < randomData.length; i += 2) { //iterate over every row returned
//            List<Label> labelList = new ArrayList<>();
//            HBox hboxx = new HBox();
//            Label l1 = new Label(randomData[i]);
//            l1.setMinHeight(30);
//            l1.setMinWidth(120);
//            l1.setAlignment(Pos.CENTER);
//            l1.setStyle("-fx-border-color:#4a4947; -fx-background-color: #b5b5b5; -fx-text-fill: #e01600; -fx-font-weight: bold; -fx-font: 16 arial;");
//            labelList.add(l1);
//            if (i + 1 < randomData.length) {
//                Label l2 = new Label(randomData[i + 1]);
//                l2.setMinHeight(30);
//                l2.setMinWidth(380);
//                l2.setStyle("-fx-border-color:#4a4947;  -fx-border-radius: 5;");
//                l2.setPadding(new Insets(5));
//                labelList.add(l2);
//            }
//            hboxx.getChildren().addAll(labelList);
////            HBox.setMargin(l1, new Insets(0, 10, 0, 0));
//            hboxList.add(hboxx);
//        }
//        vboxx.getChildren().clear();
//        vboxx.getChildren().addAll(hboxList);
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            info();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
