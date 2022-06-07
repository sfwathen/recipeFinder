package com.example.recipefinder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.PreparedStatement;

public class CreateAccountController {
    @FXML private TextField createUsernameInput;
    @FXML private PasswordField createPasswordInput;
    @FXML private Label createErrorLabel;

    @FXML protected void initialize() {
        createErrorLabel.setTextFill(Color.color(1, 0, 0));
    }
    @FXML
    public void createUser() {
        String username = createUsernameInput.getText();
        String password = createPasswordInput.getText();

        if (username.length() < 2) {
            createErrorLabel.setText("Username must be at least 2 characters");
            return;
        }

        if (password.length() < 5) {
            createErrorLabel.setText("Password must be at least 5 characters");
            return;
        }

        try {
            String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(insertSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            RecipeFinder.currentUser = username;
            System.out.println("User Successfully Created");
            RecipeFinder.navigateToNewPage("main-menu-view.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("User already exists");
            createErrorLabel.setText("User already exists");
        }
    }

    @FXML
    protected void goBackToLogin() throws IOException {
        RecipeFinder.navigateToNewPage("login-view.fxml");
    }
}
