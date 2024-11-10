module edu.ijse.mvc.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;

    opens edu.ijse.mvc.finalproject.controller to javafx.fxml;
    opens edu.ijse.mvc.finalproject.dto.tm to javafx.base;
    exports edu.ijse.mvc.finalproject;
}