module com.example.cat201_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires json.simple;

    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}