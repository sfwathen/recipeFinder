package com.example.recipefinder;

import com.mysql.cj.conf.BooleanProperty;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private ListView ingredientListView;

    @FXML
    private ListView resultsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ListView<Item> listView = new ListView<>();
        for (int i=1; i<=20; i++) {
            Item item = new Item("Item "+i, false);

            // observe item's on property and display message if it changes:
            item.onProperty().addListener((obs, wasOn, isNowOn) -> {
                System.out.println(item.getName() + " changed on state from "+wasOn+" to "+isNowOn);
            });

            listView.getItems().add(item);
        }

        ingredientListView.setCellFactory(CheckBoxListCell.forListView(new Callback<Item, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Item item) {
                return item.onProperty();
            }
        }));

    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        RecipeFinder.navigateToNewPage("main-menu-view.fxml");
    }
}





