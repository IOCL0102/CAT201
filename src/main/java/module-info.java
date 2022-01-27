module com.example.payment_module_cinema_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires core;
    //requires javase;


    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}