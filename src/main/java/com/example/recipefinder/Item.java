package com.example.recipefinder;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleStringProperty ingredientName = new SimpleStringProperty();
    private final SimpleBooleanProperty checked = new SimpleBooleanProperty();

    public Item(String name, boolean on) {
        setName(name);
        setOn(on);
    }

    public final SimpleStringProperty nameProperty() {
        return this.ingredientName;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.nameProperty().set(name);
    }

    public final SimpleBooleanProperty onProperty() {
        return this.checked;
    }

    public final boolean isOn() {
        return this.onProperty().get();
    }

    public final void setOn(final boolean on) {
        this.onProperty().set(on);
    }

    @Override
    public String toString() {
        return getName();
    }

}
