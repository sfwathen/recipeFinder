module com.example.recipefinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.recipefinder to javafx.fxml;
    exports com.example.recipefinder;
}