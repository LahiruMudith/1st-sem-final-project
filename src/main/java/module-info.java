module edu.ijse.mvc.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens edu.ijse.mvc.finalproject.controller to javafx.fxml;
    exports edu.ijse.mvc.finalproject;
}