package com.example.recipefinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;


import static java.lang.Class.forName;
import static javafx.application.ConditionalFeature.FXML;

public class RecipeFinder extends Application {
    public static String currentUser = "admin";
    public static int currentDishID;
    public static String previousView;

    private static Stage stage;

    static final int HEIGHT = 400;
    static final int WIDTH = 600;

    public static Connection conn;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/atds93", "atds93", "newpass123");
        } catch (Exception e) {
            e.printStackTrace();
       }
        RecipeFinder.stage = stage;
        stage.setTitle("RECIPEfinder");
        RecipeFinder.navigateToNewPage("login-view.fxml");
    }

    public static void navigateToNewPage(String pageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeFinder.class.getResource(pageName));

        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToNewPage(String pageName, int dishID) throws IOException {
        currentDishID = dishID;
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeFinder.class.getResource(pageName));

        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);

        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}