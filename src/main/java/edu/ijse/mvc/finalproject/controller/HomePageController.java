package edu.ijse.mvc.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController {
    @FXML
    private ImageView btnMinimize;

    public void btnClose(MouseEvent mouseEvent) {
        System.exit(0);
    }
    public void btnMinimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    public void btnMaximize(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(false);
        }else {
            stage.setMaximized(true);
        }
    }
}
