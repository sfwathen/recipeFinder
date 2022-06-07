package com.example.recipefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommunityController implements Initializable {
    @FXML
    private ListView resultsListView;

    @FXML
    protected void goToHome() throws IOException {
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }

    @FXML
    void info() throws SQLException {
        String selectSQL = "SELECT * FROM dish";
        PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(selectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        ObservableList<HBox> results = FXCollections.observableArrayList();

        while(resultSet.next()){
            HBoxCell result = new HBoxCell(resultSet.getString("dishName"), resultSet.getInt("dishID"), "community-view.fxml");
            results.add(result);
        }

        resultsListView.setItems(results);
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
