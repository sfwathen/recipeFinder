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

    @FXML
    protected void goToSearch() throws IOException {
        RecipeFinder.navigateToNewPage("search-view.fxml");
    }

    @FXML
    protected void goToAddRecipes() throws IOException {
        RecipeFinder.navigateToNewPage("addRecipe.fxml");
    }
}
