package com.example.recipefinder;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {
    @FXML
    protected void goToCommunity() throws IOException {
        RecipeFinder.navigateToNewPage("community-view.fxml");
    }

    @FXML
    protected void goToSaved() throws IOException {
        RecipeFinder.navigateToNewPage("saved-view.fxml");
    }
}
