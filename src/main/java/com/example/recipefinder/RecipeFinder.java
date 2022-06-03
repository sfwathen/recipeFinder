package com.example.recipefinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

import static java.lang.Class.forName;

public class RecipeFinder extends Application {
    public String currentUser;
    public int currentDishID;
    public String previousView;

    public static Connection conn;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/atds93", "atds93", "newpass123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeFinder.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("RECIPEfinder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}