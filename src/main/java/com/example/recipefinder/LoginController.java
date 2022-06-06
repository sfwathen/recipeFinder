package com.example.recipefinder;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    protected void userLogin() throws IOException {
        String username = "Dean12";
        String passAttempt = "password";

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
                }
                else {
                    System.out.println("Incorrect Password");
                }
            }
            catch (Exception e) {
                System.out.println("User does not exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


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
