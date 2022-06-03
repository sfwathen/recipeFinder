module com.example.recipefinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.recipefinder to javafx.fxml;
    exports com.example.recipefinder;
}