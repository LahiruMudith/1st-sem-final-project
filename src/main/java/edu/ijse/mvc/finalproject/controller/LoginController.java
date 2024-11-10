package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.AppInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAdminId.setFocusTraversable(false);
        txtPassword.setFocusTraversable(false);
        btnLogin.setDisable(true);
    }

    @FXML
    private AnchorPane ancrLogin;

    @FXML
    private ImageView btnLogin;

    @FXML
    private TextField txtAdminId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnGoToLogin(MouseEvent event) {
    }

    @FXML
    void btnGoToSignIn(MouseEvent event) {
        try {
            ancrLogin.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/signIn.fxml"));
            ancrLogin.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btnLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.TRANSPARENT);
            stage1.setMaximized(true);
            stage1.setResizable(true);
            stage1.centerOnScreen();
            Stage window = (Stage) btnLogin.getScene().getWindow();
            window.close();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
