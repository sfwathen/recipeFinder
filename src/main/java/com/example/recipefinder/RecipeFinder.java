package com.example.recipefinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class RecipeFinder extends Application {
    public static String currentUser = "admin";
    public static int currentDishID = 1;
    public static String previousView;

    public static Connection conn;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/atds93", "atds93", "newpass123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeFinder.class.getResource("dish-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("RECIPEfinder");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            try{
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}