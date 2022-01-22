module com.example.payment_module_cinema_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.payment_module_cinema_project to javafx.fxml;
    exports com.example.payment_module_cinema_project;
}