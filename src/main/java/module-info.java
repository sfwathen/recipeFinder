module com.example.recipefinder {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.recipefinder to javafx.fxml;
    exports com.example.recipefinder;
}