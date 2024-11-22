package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.DataValidate.DataValidate;
import edu.ijse.mvc.finalproject.dto.SignInDto;
import edu.ijse.mvc.finalproject.model.SignInModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    SignInModel signInModel = new SignInModel();
    DataValidate dataValidate = new DataValidate();

    @FXML
    private ImageView btnSignIn;

    @FXML
    private AnchorPane anchrSignIn;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAdminId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label txtError;

    @FXML
    void btnClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btnGoToLogin(MouseEvent event) {
        try {
            anchrSignIn.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            anchrSignIn.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnGoToSignIn(MouseEvent event) {

    }

    @FXML
    void btnSignIn(ActionEvent event) {
        txtName.setStyle(txtName.getStyle() + "; -fx-border-color:  #FFFFFF; -fx-border-width: 0 0 0 0");
        txtPassword.setStyle(txtPassword.getStyle() + "; -fx-border-color:  #FFFFFF; -fx-border-width: 0 0 0 0");
        txtAddress.setStyle(txtAddress.getStyle() + "; -fx-border-color:  #FFFFFF; -fx-border-width: 0 0 0 0");

        String address = txtAddress.getText();
        String adminId = txtAdminId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        if (adminId.isEmpty() && address.isEmpty() && name.isEmpty() && password.isEmpty()) {
            txtError.setText("Please fill all the fields");
        }else {

            boolean validatePasswoord = dataValidate.validatePasswoord(password);

            if (!validatePasswoord){
                txtPassword.setStyle(txtPassword.getStyle() + "; -fx-border-color: red; -fx-border-width: 0 0 2 0 ");
            }

            if (validatePasswoord) {
                SignInDto signInDto = new SignInDto(
                        txtAdminId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtPassword.getText()
                );

                boolean result = signInModel.SaveAdmin(signInDto);
                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sign In");
                    alert.setHeaderText("Admin Saved Successfully");
                    alert.showAndWait();
                    clearFormate();
                    try {
                        anchrSignIn.getChildren().clear();
                        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
                        anchrSignIn.getChildren().add(load);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Sign In");
                    alert.setHeaderText("Admin Saved Unsuccessfully");
                    alert.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSignIn.setDisable(true);
        txtAdminId.setEditable(false);
        setNextCustomerId();
    }

    public void setNextCustomerId(){
        String newId = signInModel.getNextAdminId();
        txtAdminId.setText(newId);
    }

    public void clearFormate(){
        txtName.clear();
        txtAddress.clear();
        txtPassword.clear();
        setNextCustomerId();
    }
}