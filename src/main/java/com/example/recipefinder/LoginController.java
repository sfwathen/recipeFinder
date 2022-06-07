package com.example.recipefinder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML private TextField usernameInput;
    @FXML private PasswordField passwordInput;
    @FXML private Label loginErrorLabel;

    @FXML protected void initialize() {
        loginErrorLabel.setTextFill(Color.color(1, 0, 0));
    }

    @FXML
    protected void userLogin() throws IOException {
        String username = usernameInput.getText();
        String passAttempt = passwordInput.getText();

        if (username.length() <= 1) {
            System.out.println("Username must be at least 2 characters");
            loginErrorLabel.setText("Username must be at least 2 characters");
            return;
        }
        else if (passAttempt.length() < 5) {
            System.out.println("Password must be at least 5 characters");
            loginErrorLabel.setText("Password must be at least 5 characters");
            return;
        }


        try {
            String selectSQL = "SELECT password FROM users WHERE username=?";
            PreparedStatement preparedStatement = RecipeFinder.conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            try {
                rs.next();
                String passActual = rs.getString(1);

                if (passActual.equals(passAttempt)) {
                    System.out.println("Successful Login");
                    RecipeFinder.currentUser = username;
                    RecipeFinder.navigateToNewPage("main-menu-view.fxml");
                }
                else {
                    System.out.println("Incorrect Password");
                    loginErrorLabel.setText("Incorrect Password");
                }
            }
            catch (Exception e) {
                System.out.println("User does not exist");
                loginErrorLabel.setText("User does not exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void goToCreateAccount() throws IOException {
        RecipeFinder.navigateToNewPage("create-account-view.fxml");
    }

}
