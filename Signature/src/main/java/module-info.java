module com.example.signature {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.signature to javafx.fxml;
    exports com.example.signature;
}