module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jna;
    requires vlcj;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}