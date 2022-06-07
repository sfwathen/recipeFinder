package com.example.recipefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private ListView ingredientListView;

    @FXML
    private ListView resultsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            Statement temp_table = RecipeFinder.conn.createStatement();
            temp_table.execute("create table if not exists searchlist"+ RecipeFinder.currentUser +" (ingredient varchar(50) primary key);");

            Statement search = RecipeFinder.conn.createStatement();
            ResultSet rs = search.executeQuery("select distinct ingredient from recipe;");

            while (rs.next()){
                Item item = new Item(rs.getString("ingredient"), false);

                // observe item's on property and display message if it changes:
                item.onProperty().addListener((obs, wasOn, isNowOn) -> {
                    try {
                        Statement addOrRemove = RecipeFinder.conn.createStatement();
                        if (!wasOn){
                            addOrRemove.execute("insert into searchlist" + RecipeFinder.currentUser +"(ingredient) values (\"" + item.getName() +"\");");
                        }
                        else{
                            addOrRemove.execute("delete from searchlist"+ RecipeFinder.currentUser +" where ingredient = \"" + item.getName() + "\"");
                        }

                        ObservableList<HBox> results = FXCollections.observableArrayList();

                        Statement fetchResults = RecipeFinder.conn.createStatement();
                        ResultSet resultSet = fetchResults.executeQuery("select * from dish d where d.dishID not " +
                                "in(select dID from recipe r " +
                                "where r.ingredient not in (select ingredient from searchlist" + RecipeFinder.currentUser +"));");
                        while(resultSet.next()){
                            HBoxCell result = new HBoxCell(resultSet.getString("dishName"), resultSet.getInt("dishID"));
                            results.add(result);
                        }

                        resultsListView.setItems(results);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });
                ingredientListView.getItems().add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ingredientListView.setCellFactory(CheckBoxListCell.forListView(Item::onProperty));

    }

    public void goToHome() throws IOException, SQLException {
        Statement drop_table = RecipeFinder.conn.createStatement();
        drop_table.execute("drop table if exists searchlist"+ RecipeFinder.currentUser);
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }
}





