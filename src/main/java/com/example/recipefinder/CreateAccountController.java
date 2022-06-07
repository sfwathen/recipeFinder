package com.example.recipefinder;

import javafx.fxml.FXML;

import java.sql.PreparedStatement;

public class CreateAccountController {
    @FXML
    public void createUser() {
        String username = "Tiger1";
        String password = "tigerPass";

        try {
            String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(insertSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();

            RecipeFinder.currentUser = username;
            System.out.println("User Successfully Created");
        }
        catch (Exception e) {
            System.out.println("User already exists");
        }
    }
}
